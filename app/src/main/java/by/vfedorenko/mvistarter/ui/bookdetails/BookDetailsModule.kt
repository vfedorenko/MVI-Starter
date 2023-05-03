package by.vfedorenko.mvistarter.ui.bookdetails

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
internal interface BookDetailsMiddlewares {

    @IntoSet
    @Binds
    fun provideGetBookMiddleware(impl: GetBookMiddleware): Middleware<BookDetailsState>

    @IntoSet
    @Binds
    fun provideFavoriteBookMiddleware(impl: FavoriteBookMiddleware): Middleware<BookDetailsState>

    @Module
    @InstallIn(ViewModelComponent::class)
    object BookDetailsModule {
        @Provides
        @ViewModelScoped
        fun provideBookDetailsStore(
            middlewares: Set<@JvmSuppressWildcards Middleware<BookDetailsState>>,
            commonMiddlewares: Set<@JvmSuppressWildcards Middleware<MviState>>
        ): MviStore<BookDetailsState> = MviStore(
            initialState = BookDetailsState(),
            reducer = BookDetailsReducer(),
            middlewares = middlewares,
            commonMiddlewares = commonMiddlewares
        )
    }
}
