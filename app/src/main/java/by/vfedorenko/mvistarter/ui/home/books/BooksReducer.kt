package by.vfedorenko.mvistarter.ui.home.books

import by.vfedorenko.starter.MviIntent
import by.vfedorenko.starter.Reducer
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject
import javax.inject.Singleton

@ViewModelScoped
class BooksReducer @Inject constructor() : Reducer<BooksState> {

    override fun reduce(state: BooksState, intent: MviIntent) = when (intent) {
        is BooksIntent.BooksReady -> state.copy(books = intent.books)
        else -> state
    }
}
