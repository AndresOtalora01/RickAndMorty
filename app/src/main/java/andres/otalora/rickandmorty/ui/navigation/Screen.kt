package andres.otalora.rickandmorty.ui.navigation

sealed class Screen(val route: String) {
    object CharactersList : Screen("charactersList")
    object DetailedCharacter : Screen("detailedCharacter")
}
