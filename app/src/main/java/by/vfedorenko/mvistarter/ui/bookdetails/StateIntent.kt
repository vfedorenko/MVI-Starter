package by.vfedorenko.mvistarter.ui.bookdetails

import by.vfedorenko.mvistarter.data.entities.Book
import by.vfedorenko.starter.MviIntent
import by.vfedorenko.starter.MviState

sealed interface BookDetailsIntent : MviIntent {
    data class GetBook(val bookId: Int) : MviIntent
    data class BookReady(val book: Book) : MviIntent
    object OnFavoriteClick : MviIntent
}

data class BookDetailsState(
    val book: Book = Book.empty
) : MviState
