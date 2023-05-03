package by.vfedorenko.mvistarter.ui.login

import by.vfedorenko.starter.MviIntent
import by.vfedorenko.starter.MviState

sealed interface LoginIntent : MviIntent {
    data class EmailChanged(val value: String) : MviIntent
    data class PasswordChanged(val value: String) : MviIntent
    object PerformLogin : MviIntent

    data class Failure(val value: String) : MviIntent
}

data class LoginState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val error: String = ""
) : MviState
