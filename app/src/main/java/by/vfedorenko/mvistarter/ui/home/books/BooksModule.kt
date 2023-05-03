package by.vfedorenko.mvistarter.ui.home.books

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
internal interface BooksMiddlewares {

    @IntoSet
    @Binds
    fun provideObserveBooksMiddleware(impl: ObserveBooksMiddleware): Middleware<BooksState>

    @IntoSet
    @Binds
    fun provideRefreshBooksMiddleware(impl: RefreshBooksMiddleware): Middleware<BooksState>

    @IntoSet
    @Binds
    fun provideSelectBookMiddleware(impl: SelectBookMiddleware): Middleware<BooksState>

    @IntoSet
    @Binds
    fun provideFavoriteBookMiddleware(impl: FavoriteBookMiddleware): Middleware<BooksState>

    @Module
    @InstallIn(ViewModelComponent::class)
    object BooksModule {
        @Provides
        @ViewModelScoped
        fun provideBooksStore(
            middlewares: Set<@JvmSuppressWildcards Middleware<BooksState>>,
            commonMiddlewares: Set<@JvmSuppressWildcards Middleware<MviState>>
        ): MviStore<BooksState> = MviStore(
            initialState = BooksState(),
            reducer = BooksReducer(),
            middlewares = middlewares,
            commonMiddlewares = commonMiddlewares
        )
    }
}


