package by.vfedorenko.mvistarter.ui.login

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
internal interface LoginMiddlewares {

    @IntoSet
    @Binds
    fun provideLoginMiddleware(impl: PerformLoginMiddleware): Middleware<LoginState>

    @Module
    @InstallIn(ViewModelComponent::class)
    object LoginModule {
        @Provides
        @ViewModelScoped
        fun provideLoginStore(
            middlewares: Set<@JvmSuppressWildcards Middleware<LoginState>>,
            commonMiddlewares: Set<@JvmSuppressWildcards Middleware<MviState>>
        ): MviStore<LoginState> = MviStore(
            initialState = LoginState(),
            reducer = LoginReducer(),
            middlewares = middlewares,
            commonMiddlewares = commonMiddlewares
        )
    }
}
