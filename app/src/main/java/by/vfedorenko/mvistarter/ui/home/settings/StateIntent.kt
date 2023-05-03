package by.vfedorenko.mvistarter.ui.home.settings

import by.vfedorenko.starter.MviIntent
import by.vfedorenko.starter.MviState

sealed interface SettingsIntent : MviIntent {
    object Logout : MviIntent
}

data class SettingsState(
    val value: String = "Settings"
) : MviState