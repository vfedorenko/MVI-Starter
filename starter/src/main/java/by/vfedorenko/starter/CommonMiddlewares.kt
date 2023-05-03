package by.vfedorenko.starter

import by.vfedorenko.starter.navigation.NavigationManager
import by.vfedorenko.starter.navigation.OpenAlertDialog
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.multibindings.IntoSet
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import timber.log.Timber
import javax.inject.Inject

class CommonErrorHandlingMiddleware @Inject constructor(
    private val navigationManager: NavigationManager
) : Middleware<MviState> {

    override fun execute(
        intent: MviIntent,
        state: MviState,
        outputIntents: MutableSharedFlow<MviIntent>,
        coroutineScope: CoroutineScope
    ) {
        intent.tryCast<GenericIntent.Failure> {
            Timber.e(error, "Failure")

            if (showAlert) {
                navigationManager.navigate(OpenAlertDialog())
            }
        }
    }
}

@Module
@InstallIn(ActivityRetainedComponent::class)
interface CommonMiddlewaresModule {

    @IntoSet
    @Binds
    fun bindCommonErrorHandlingMiddleware(impl: CommonErrorHandlingMiddleware): Middleware<MviState>
}
