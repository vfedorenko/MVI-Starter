package by.vfedorenko.starter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

abstract class BaseViewModel<State : MviState>(
    private val store: MviStore<State>
) : ViewModel() {

    val state = store.state.asStateFlow()

    init {
        store.init(viewModelScope)
    }

    fun acceptIntent(intent: MviIntent) {
        store.intents.tryEmit(intent)
    }
}
