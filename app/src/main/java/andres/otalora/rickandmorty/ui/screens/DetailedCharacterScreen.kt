package andres.otalora.rickandmorty.ui.screens

import andres.otalora.rickandmorty.ui.viewmodel.SharedViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter

@Composable
fun DetailedCharacterScreen(sharedViewModel: SharedViewModel) {
    val selectedCharacter = sharedViewModel.selectedCharacter.observeAsState()

    selectedCharacter.value?.let { character ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                modifier = Modifier
                    .padding(top = 64.dp, bottom = 16.dp, start = 16.dp, end = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val imagePainter = rememberImagePainter(data = character.image)

                Box(
                    modifier = Modifier
                        .size(200.dp)
                        .clip(shape = RoundedCornerShape(8.dp))
                        .background(Color.White)
                ) {
                    Image(
                        painter = imagePainter,
                        contentDescription = "Character Image",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = character.name,
                    style = MaterialTheme.typography.h4.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontFamily = FontFamily.Cursive
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Status: ${character.status}",
                    style = MaterialTheme.typography.h6.copy(
                        color = Color.White,
                        fontFamily = FontFamily.Cursive
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Species: ${character.species}",
                    style = MaterialTheme.typography.h6.copy(
                        color = Color.White,
                        fontFamily = FontFamily.Cursive
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Gender: ${character.gender}",
                    style = MaterialTheme.typography.h6.copy(
                        color = Color.White,
                        fontFamily = FontFamily.Cursive
                    )
                )
            }
        }
    }
}
