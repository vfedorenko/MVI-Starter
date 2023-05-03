package by.vfedorenko.mvistarter.ui.home.favorites

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
internal interface FavoritesMiddlewares {

    @IntoSet
    @Binds
    fun provideObserveFavoritesMiddleware(impl: ObserveFavoritesMiddleware): Middleware<FavoritesState>

    @IntoSet
    @Binds
    fun provideSelectBookMiddleware(impl: SelectBookMiddleware): Middleware<FavoritesState>

    @IntoSet
    @Binds
    fun provideFavoriteBookMiddleware(impl: FavoriteBookMiddleware): Middleware<FavoritesState>

    @Module
    @InstallIn(ViewModelComponent::class)
    object FavoritesModule {
        @Provides
        @ViewModelScoped
        fun provideFavoritesStore(
            middlewares: Set<@JvmSuppressWildcards Middleware<FavoritesState>>,
            commonMiddlewares: Set<@JvmSuppressWildcards Middleware<MviState>>
        ): MviStore<FavoritesState> = MviStore(
            initialState = FavoritesState(),
            reducer = FavoritesReducer(),
            middlewares = middlewares,
            commonMiddlewares = commonMiddlewares
        )
    }
}
