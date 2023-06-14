package andres.otalora.rickandmorty.ui.theme


import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Typography
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val ZaraPrimary = Color(0xFF000000)
private val ZaraSecondary = Color(0xFFFFFFFF)
private val ZaraBackground = Color(0xFF808080)
private val ZaraError = Color(0xFFD32F2F)

private val ZaraOnPrimary = Color(0xFFFFFFFF)
private val ZaraOnSecondary = Color(0xFF000000)
private val ZaraOnError = Color(0xFFD32F2F)

private val ZaraColorPalette = darkColors(
    primary = ZaraPrimary,
    secondary = ZaraSecondary,
    background = ZaraBackground,
    error = ZaraError,
    onPrimary = ZaraOnPrimary,
    onSecondary = ZaraOnSecondary,
    onError = ZaraOnError
)

private val ZaraTypography = Typography(
    h1 = TextStyle(fontSize = 30.sp, fontWeight = FontWeight.Bold, color = ZaraOnPrimary),
    body1 = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Normal, color = ZaraOnSecondary)
)

private val ZaraShapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(8.dp),
    large = RoundedCornerShape(0.dp)
)

@Composable
fun RickAndMortyTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = ZaraColorPalette,
        typography = ZaraTypography,
        shapes = ZaraShapes,
        content = content
    )
}