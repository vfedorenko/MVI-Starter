package by.vfedorenko.mvistarter.ui.home.settings

import by.vfedorenko.starter.BaseViewModel
import by.vfedorenko.starter.MviStore
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    store: MviStore<SettingsState>
) : BaseViewModel<SettingsState>(store)
