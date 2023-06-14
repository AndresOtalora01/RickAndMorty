package andres.otalora.rickandmorty.ui.screens

import andres.otalora.rickandmorty.R
import andres.otalora.rickandmorty.domain.CharacterModel
import andres.otalora.rickandmorty.ui.navigation.Screen
import andres.otalora.rickandmorty.ui.viewmodel.CharactersViewModel
import andres.otalora.rickandmorty.ui.viewmodel.SharedViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation

@Composable
fun CharactersListScreen(
    viewModel: CharactersViewModel, // Injected ViewModel
    navController: NavController, // Navigation controller for screen transitions
    sharedViewModel: SharedViewModel // Shared ViewModel for sharing data between composable
) {
    // Collect the flow of paging data as a lazy paging item
    val characters = viewModel.charactersPagingFlow.collectAsLazyPagingItems()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        // List of character items
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            item {
                Text(
                    text = "Rick and Morty x Zara",
                    style = MaterialTheme.typography.h4.copy(
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Cursive,
                        color = Color.White
                    ),
                    modifier = Modifier
                        .padding(8.dp)
                )
            }

            // Dynamic list of character items
            items(characters) { character ->
                CharacterItem(
                    character = character,
                    navController = navController,
                    sharedViewModel = sharedViewModel
                )
            }
        }

        // Handle loading and error states
        when (val loadState = characters.loadState.refresh) {
            is LoadState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = Color.White
                )
            }
            is LoadState.Error -> {
                // Error handling with retry option
                val errorMessage = viewModel.transformError(loadState.error)
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = errorMessage,
                        style = MaterialTheme.typography.h4.copy(
                            color = Color.White,
                            fontFamily = FontFamily.Cursive
                        )
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = { characters.retry() }, // Retry loading data
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                            .background(Color.White),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
                    ) {
                        Text(text = "Retry")
                    }
                }
            }
        }
    }
}

@Composable
fun CharacterItem(
    character: CharacterModel?, // Character data
    navController: NavController, // Navigation controller for screen transitions
    sharedViewModel: SharedViewModel // Shared ViewModel for sharing data between composable
) {
    // Card layout for each character
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                character?.let {
                    // Set selected character and navigate to detail screen on click
                    sharedViewModel.setSelectedCharacter(it)
                    navController.navigate(Screen.DetailedCharacter.route)
                }
            },
        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp),
        backgroundColor = Color.White,
        contentColor = Color.Black
    ) {
        // Character details (image and name)
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            // Image loading with Coil
            val imagePainter = rememberImagePainter(
                data = character?.image,
                builder = {
                    placeholder(R.drawable.placeholder)
                    error(R.drawable.error)
                    crossfade(true)
                    transformations(CircleCropTransformation())
                }
            )
            Image(
                painter = imagePainter,
                contentDescription= "Character Image",
                modifier = Modifier
                    .size(72.dp)
                    .clip(shape = CircleShape), // Clip image to circle shape
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = character?.name ?: "",
                style = MaterialTheme.typography.h5.copy(
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Cursive
                )
            )
        }
    }
}