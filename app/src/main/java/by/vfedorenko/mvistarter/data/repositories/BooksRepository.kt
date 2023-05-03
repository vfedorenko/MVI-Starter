package by.vfedorenko.mvistarter.data.repositories

import by.vfedorenko.mvistarter.data.api.BooksApi
import javax.inject.Inject

class BooksRepository @Inject constructor(
    private val booksApi: BooksApi
) {
    suspend fun getBooks() = booksApi.getBooks()
}