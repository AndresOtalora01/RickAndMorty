package andres.otalora.rickandmorty.data.model

data class InfoDto(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)