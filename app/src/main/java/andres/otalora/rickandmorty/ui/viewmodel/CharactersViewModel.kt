package andres.otalora.rickandmorty.ui.viewmodel

import andres.otalora.rickandmorty.domain.CharacterModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    pager: Pager<Int, CharacterModel>
) : ViewModel() {
    val charactersPagingFlow: Flow<PagingData<CharacterModel>> = pager
        .flow
        .distinctUntilChanged()
        .cachedIn(viewModelScope)

    fun transformError(error: Throwable): String {
        return when (error) {
            is IOException -> "No internet connection"
            else -> "Unknown error"
        }
    }
}