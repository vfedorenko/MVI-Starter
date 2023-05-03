package by.vfedorenko.mvistarter.ui.home.books

import by.vfedorenko.mvistarter.domain.BooksInteractor
import by.vfedorenko.starter.GenericIntent
import by.vfedorenko.starter.Middleware
import by.vfedorenko.starter.MviIntent
import by.vfedorenko.starter.navigation.Forward
import by.vfedorenko.starter.navigation.NavigationDirections
import by.vfedorenko.starter.navigation.NavigationManager
import by.vfedorenko.starter.tryCast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNot
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class ObserveBooksMiddleware @Inject constructor(
    private val booksInteractor: BooksInteractor
) : Middleware<BooksState> {

    override fun execute(
        intent: MviIntent,
        state: BooksState,
        outputIntents: MutableSharedFlow<MviIntent>,
        coroutineScope: CoroutineScope
    ) {
        intent.tryCast<GenericIntent.Init> {
            booksInteractor.observeBooks()
                .filterNot { it.isEmpty() }
                .onEach { outputIntents.tryEmit(BooksIntent.BooksReady(it)) }
                .catch { outputIntents.tryEmit(GenericIntent.Failure(it)) }
                .launchIn(coroutineScope)
        }
    }
}

class RefreshBooksMiddleware @Inject constructor(
    private val booksInteractor: BooksInteractor
) : Middleware<BooksState> {

    override fun execute(
        intent: MviIntent,
        state: BooksState,
        outputIntents: MutableSharedFlow<MviIntent>,
        coroutineScope: CoroutineScope
    ) {
        intent.tryCast<GenericIntent.Init> {
            coroutineScope.launch {
                try {
                    outputIntents.tryEmit(GenericIntent.Loading)
                    booksInteractor.refreshBooks()
                } catch (error: Throwable) {
                    outputIntents.tryEmit(GenericIntent.Failure(error))
                }
            }
        }
    }
}

class SelectBookMiddleware @Inject constructor(
    private val navigationManager: NavigationManager
) : Middleware<BooksState> {

    override fun execute(
        intent: MviIntent,
        state: BooksState,
        outputIntents: MutableSharedFlow<MviIntent>,
        coroutineScope: CoroutineScope
    ) {
        intent.tryCast<BooksIntent.OnBookClick> {
            navigationManager.navigate(
                Forward(NavigationDirections.BookDetails(book.id))
            )
        }
    }
}

class FavoriteBookMiddleware @Inject constructor(
    private val booksInteractor: BooksInteractor
) : Middleware<BooksState> {

    override fun execute(
        intent: MviIntent,
        state: BooksState,
        outputIntents: MutableSharedFlow<MviIntent>,
        coroutineScope: CoroutineScope
    ) {
        intent.tryCast<BooksIntent.OnFavoriteClick> {
            booksInteractor.favoriteBook(book.id, !book.isFavorite)
        }
    }
}
