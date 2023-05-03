package by.vfedorenko.mvistarter.ui.home.favorites

import by.vfedorenko.starter.MviIntent
import by.vfedorenko.starter.Reducer
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject
import javax.inject.Singleton

@ViewModelScoped
class FavoritesReducer @Inject constructor() : Reducer<FavoritesState> {

    override fun reduce(state: FavoritesState, intent: MviIntent) = when (intent) {
        is FavoritesIntent.BooksReady -> state.copy(books = intent.books)
        else -> state
    }
}
