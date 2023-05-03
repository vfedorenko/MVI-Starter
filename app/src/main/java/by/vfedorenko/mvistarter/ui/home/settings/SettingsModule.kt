package by.vfedorenko.mvistarter.ui.home.settings

import by.vfedorenko.starter.Middleware
import by.vfedorenko.starter.MviState
import by.vfedorenko.starter.MviStore
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.multibindings.IntoSet

@Module
@InstallIn(ViewModelComponent::class)
internal interface SettingsMiddlewares {

    @IntoSet
    @Binds
    fun provideLogoutMiddleware(impl: LogoutMiddleware): Middleware<SettingsState>

    @Module
    @InstallIn(ViewModelComponent::class)
    object SettingsModule {
        @Provides
        @ViewModelScoped
        fun provideSettingsStore(
            middlewares: Set<@JvmSuppressWildcards Middleware<SettingsState>>,
            commonMiddlewares: Set<@JvmSuppressWildcards Middleware<MviState>>
        ): MviStore<SettingsState> = MviStore(
            initialState = SettingsState(),
            reducer = SettingsReducer(),
            middlewares = middlewares,
            commonMiddlewares = commonMiddlewares
        )
    }
}
