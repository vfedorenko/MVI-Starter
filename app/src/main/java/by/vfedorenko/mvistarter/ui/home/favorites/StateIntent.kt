package by.vfedorenko.mvistarter.ui.home.favorites

import by.vfedorenko.mvistarter.core.filledList
import by.vfedorenko.mvistarter.data.entities.Book
import by.vfedorenko.starter.MviIntent
import by.vfedorenko.starter.MviState

sealed interface FavoritesIntent : MviIntent {
    data class BooksReady(val books: List<Book>) : MviIntent
    data class OnBookClick(val book: Book) : MviIntent
    data class OnFavoriteClick(val book: Book) : MviIntent
}

data class FavoritesState(
    val books: List<Book> = filledList(Book.empty, 10)
) : MviState
