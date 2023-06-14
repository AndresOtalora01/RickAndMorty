package andres.otalora.rickandmorty.data.remote

import andres.otalora.rickandmorty.data.model.CharactersListDto
import retrofit2.http.GET
import retrofit2.http.Query

interface RickAndMortyApi {
    @GET(CHARACTERS_ENDPOINT)
    suspend fun getCharactersByPage(@Query("page") page: Int): CharactersListDto

    companion object ApiConstants {
        const val BASE_URL = "https://rickandmortyapi.com/api/"
        const val CHARACTERS_ENDPOINT = "character"
    }
}
