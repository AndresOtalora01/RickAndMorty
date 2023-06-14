package andres.otalora.rickandmorty.data.remote



import andres.otalora.rickandmorty.data.mappers.mapToModel
import andres.otalora.rickandmorty.domain.CharacterModel
import androidx.paging.PagingSource
import androidx.paging.PagingState

class CharactersPagingSource(
    private val characterApi: RickAndMortyApi // The API service for fetching characters
) : PagingSource<Int, CharacterModel>() { // Extend PagingSource for paging data

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterModel> {
        // Try to load the data
        return try {
            val page = params.key ?: 1 // Get the key for the page or default to 1
            val characters = characterApi.getCharactersByPage(page) // Fetch characters from API
            // Determine the previous key for the paging source
            val prevKey = if (page > 1) page - 1 else null
            // Determine the next key for the paging source
            val nextKey = if (characters.results.isNotEmpty()) page + 1 else null

            // Map the API results to the model
            val characterModels = characters.results.map { characterDto ->
                characterDto.mapToModel()
            }

            // Return a successful page load result
            LoadResult.Page(
                data = characterModels,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            // If loading fails, return an error result
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, CharacterModel>): Int? {
        // Since we don't have a refresh key, we return null here
        return null
    }
}