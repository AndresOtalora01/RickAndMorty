package andres.otalora.rickandmorty.viewmodels

import andres.otalora.rickandmorty.domain.CharacterModel
import andres.otalora.rickandmorty.ui.viewmodel.SharedViewModel
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class SharedViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: SharedViewModel

    @Before
    fun setup() {
        viewModel = SharedViewModel()
    }

    @Test
    fun setSelectedCharacter_updatesSelectedCharacterValue() {
        val character = CharacterModel(
            id = 123,
            name = "John Doe",
            status = "Alive",
            species = "Human",
            gender = "Male",
            image = "https://example.com/image.jpg"
        )

        viewModel.setSelectedCharacter(character)

        assertEquals(character, viewModel.selectedCharacter.value)
    }
}