package by.vfedorenko.mvistarter.ui.login

import by.vfedorenko.starter.GenericIntent
import by.vfedorenko.starter.MviIntent
import by.vfedorenko.starter.Reducer
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class LoginReducer @Inject constructor() : Reducer<LoginState> {

    override fun reduce(state: LoginState, intent: MviIntent) = when (intent) {
        GenericIntent.Loading -> state.copy(isLoading = true)
        is LoginIntent.EmailChanged -> state.copy(
            email = intent.value,
            error = ""
        )
        is LoginIntent.PasswordChanged -> state.copy(
            password = intent.value,
            error = ""
        )
        is LoginIntent.Failure -> state.copy(
            error = intent.value,
            isLoading = false
        )
        else -> state
    }
}
