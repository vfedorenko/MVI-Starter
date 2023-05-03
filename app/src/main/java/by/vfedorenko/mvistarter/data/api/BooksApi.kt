package by.vfedorenko.mvistarter.data.api

import by.vfedorenko.mvistarter.core.filledListIndexed
import by.vfedorenko.mvistarter.data.entities.Book
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.delay
import javax.inject.Inject

@ActivityRetainedScoped
class BooksApi @Inject constructor() {

    suspend fun getBooks(): List<Book> {
        delay(500)
        return filledListIndexed(30) { Book.stub(it) }
    }
}
