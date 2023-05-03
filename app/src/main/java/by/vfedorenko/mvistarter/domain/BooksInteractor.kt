package by.vfedorenko.mvistarter.domain

import by.vfedorenko.mvistarter.data.entities.Book
import by.vfedorenko.mvistarter.data.repositories.BooksRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BooksInteractor @Inject constructor() {

    private val books = MutableStateFlow<List<Book>>(emptyList())

    fun observeBooks(): Flow<List<Book>> = books.asStateFlow()

    suspend fun refreshBooks() {
        val newBooks = mutableListOf<Book>()
        for (i in 1..11) {
            newBooks.add(
                Book(
                    id = i,
                    title = "Title $i",
                    body = "Description of a book $i",
                    isFavorite = false
                )
            )
        }
        delay(500)
        books.update { newBooks }
    }

    suspend fun getBook(id: Int): Book = withContext(Dispatchers.IO) {
        delay(500)
        books.value.first { it.id == id }
    }

    fun favoriteBook(bookId: Int, isFavorite: Boolean) {
        books.update { list ->
            list.toMutableList().apply {
                replaceAll {
                    if (it.id == bookId) it.copy(isFavorite = isFavorite) else it
                }
            }
        }
    }
}
