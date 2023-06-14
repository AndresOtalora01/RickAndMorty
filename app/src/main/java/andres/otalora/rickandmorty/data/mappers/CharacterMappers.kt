package andres.otalora.rickandmorty.data.mappers

import andres.otalora.rickandmorty.data.model.CharacterDto
import andres.otalora.rickandmorty.domain.CharacterModel


fun CharacterDto.mapToModel(): CharacterModel {
    return CharacterModel(
        id = id,
        name = name,
        status = status,
        species = species,
        gender = gender,
        image = image
    )
}
