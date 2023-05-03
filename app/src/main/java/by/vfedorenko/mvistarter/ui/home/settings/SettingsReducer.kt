package by.vfedorenko.mvistarter.ui.home.settings

import by.vfedorenko.starter.MviIntent
import by.vfedorenko.starter.Reducer
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class SettingsReducer @Inject constructor() : Reducer<SettingsState> {

    override fun reduce(state: SettingsState, intent: MviIntent) = when (intent) {
        // TODO add your action here
        else -> state
    }
}
