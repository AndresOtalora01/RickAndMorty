package andres.otalora.rickandmorty.ui.viewmodel

import andres.otalora.rickandmorty.domain.CharacterModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    private val _selectedCharacter = MutableLiveData<CharacterModel>()
    val selectedCharacter: LiveData<CharacterModel> get() = _selectedCharacter

    fun setSelectedCharacter(character: CharacterModel) {
        _selectedCharacter.value = character
    }
}