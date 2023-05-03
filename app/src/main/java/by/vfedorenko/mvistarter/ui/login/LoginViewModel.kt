package by.vfedorenko.mvistarter.ui.login

import by.vfedorenko.starter.BaseViewModel
import by.vfedorenko.starter.MviStore
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    store: MviStore<LoginState>
) : BaseViewModel<LoginState>(store)
