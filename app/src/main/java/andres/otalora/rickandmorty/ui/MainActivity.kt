package andres.otalora.rickandmorty.ui

import andres.otalora.rickandmorty.ui.navigation.Screen
import andres.otalora.rickandmorty.ui.screens.CharactersListScreen
import andres.otalora.rickandmorty.ui.screens.DetailedCharacterScreen
import andres.otalora.rickandmorty.ui.theme.RickAndMortyTheme
import andres.otalora.rickandmorty.ui.viewmodel.CharactersViewModel
import andres.otalora.rickandmorty.ui.viewmodel.SharedViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint // Marks this class as an entry point for Hilt to inject
class MainActivity : ComponentActivity() {
    // ViewModel instance provided by Hilt
    private val viewModel: CharactersViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            RickAndMortyTheme {

                Surface(color = MaterialTheme.colors.background) {
                    // Main entry point for the application UI
                    MainScreen(viewModel, sharedViewModel)
                }
            }
        }
    }

    @Composable
    fun MainScreen(
        viewModel: CharactersViewModel,
        sharedViewModel: SharedViewModel
    ) {
        // Create a NavController for navigation within the app
        val navController = rememberNavController()

        // Collect the flow of paging data as a lazy paging item
        viewModel.charactersPagingFlow.collectAsLazyPagingItems()

        // Navigation host for the composables in the app
        NavHost(navController, startDestination = Screen.CharactersList.route) {
            // Route for the character list screen
            composable(Screen.CharactersList.route) {
                CharactersListScreen(
                    viewModel = viewModel,
                    navController = navController,
                    sharedViewModel = sharedViewModel
                )
            }
            // Route for the detailed character screen
            composable(Screen.DetailedCharacter.route) {
                DetailedCharacterScreen(sharedViewModel)
            }
        }
    }
}

