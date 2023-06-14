package andres.otalora.rickandmorty.data.model

data class CharactersListDto(
    val infoDto: InfoDto,
    val results: List<CharacterDto>
)