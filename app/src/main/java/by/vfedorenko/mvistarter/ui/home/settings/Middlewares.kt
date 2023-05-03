package by.vfedorenko.mvistarter.ui.home.settings

import by.vfedorenko.starter.Middleware
import by.vfedorenko.starter.MviIntent
import by.vfedorenko.starter.navigation.NavigationDirections
import by.vfedorenko.starter.navigation.NavigationManager
import by.vfedorenko.starter.navigation.Replace
import by.vfedorenko.starter.tryCast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject

class LogoutMiddleware @Inject constructor(
    private val navigationManager: NavigationManager
) : Middleware<SettingsState> {

    override fun execute(
        intent: MviIntent,
        state: SettingsState,
        outputIntents: MutableSharedFlow<MviIntent>,
        coroutineScope: CoroutineScope
    ) {
        intent.tryCast<SettingsIntent.Logout> {
            navigationManager.navigate(Replace(NavigationDirections.Login))
        }
    }
}
