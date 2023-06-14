package andres.otalora.rickandmorty.domain

data class CharacterModel(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val image: String?
)