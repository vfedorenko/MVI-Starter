package by.vfedorenko.mvistarter.ui.bookdetails

import by.vfedorenko.starter.MviIntent
import by.vfedorenko.starter.Reducer
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class BookDetailsReducer @Inject constructor() : Reducer<BookDetailsState> {
    override fun reduce(state: BookDetailsState, intent: MviIntent) = when (intent) {
        is BookDetailsIntent.BookReady -> state.copy(book = intent.book)
        BookDetailsIntent.OnFavoriteClick -> state.copy(book = state.book.copy(isFavorite = !state.book.isFavorite))
        else -> state
    }
}
