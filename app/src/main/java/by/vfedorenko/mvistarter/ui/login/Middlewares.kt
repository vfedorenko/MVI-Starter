package by.vfedorenko.mvistarter.ui.login

import by.vfedorenko.starter.navigation.NavigationDirections
import by.vfedorenko.starter.Middleware
import by.vfedorenko.starter.MviIntent
import by.vfedorenko.starter.navigation.NavigationManager
import by.vfedorenko.starter.navigation.Replace
import by.vfedorenko.starter.tryCast
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@ViewModelScoped
class PerformLoginMiddleware @Inject constructor(
    private val navigationManager: NavigationManager
) : Middleware<LoginState> {

    override fun execute(
        intent: MviIntent,
        state: LoginState,
        outputIntents: MutableSharedFlow<MviIntent>,
        coroutineScope: CoroutineScope
    ) {
        intent.tryCast<LoginIntent.PerformLogin> {
            coroutineScope.launch {
                try {
                    delay(500)
                    navigationManager.navigate(Replace(NavigationDirections.Home))
                } catch (e: Throwable) {
                    Timber.e(e, "Failed to login")
                    outputIntents.tryEmit(
                        LoginIntent.Failure("Failed to login")
                    )
                }
            }
        }
    }
}
