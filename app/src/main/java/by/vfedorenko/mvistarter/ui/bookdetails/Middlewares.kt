package by.vfedorenko.mvistarter.ui.bookdetails

import by.vfedorenko.mvistarter.domain.BooksInteractor
import by.vfedorenko.starter.GenericIntent
import by.vfedorenko.starter.Middleware
import by.vfedorenko.starter.MviIntent
import by.vfedorenko.starter.tryCast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class GetBookMiddleware @Inject constructor(
    private val booksInteractor: BooksInteractor
) : Middleware<BookDetailsState> {

    override fun execute(
        intent: MviIntent,
        state: BookDetailsState,
        outputIntents: MutableSharedFlow<MviIntent>,
        coroutineScope: CoroutineScope
    ) {
        intent.tryCast<BookDetailsIntent.GetBook> {
            coroutineScope.launch {
                try {
                    outputIntents.tryEmit(
                        BookDetailsIntent.BookReady(booksInteractor.getBook(bookId))
                    )
                } catch (e: Exception) {
                    outputIntents.tryEmit(GenericIntent.Failure(e))
                }
            }
        }
    }
}

class FavoriteBookMiddleware @Inject constructor(
    private val booksInteractor: BooksInteractor
) : Middleware<BookDetailsState> {

    override fun execute(
        intent: MviIntent,
        state: BookDetailsState,
        outputIntents: MutableSharedFlow<MviIntent>,
        coroutineScope: CoroutineScope
    ) {
        intent.tryCast<BookDetailsIntent.OnFavoriteClick> {
            booksInteractor.favoriteBook(state.book.id, !state.book.isFavorite)
        }
    }
}
