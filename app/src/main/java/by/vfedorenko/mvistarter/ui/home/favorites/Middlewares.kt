package by.vfedorenko.mvistarter.ui.home.favorites

import by.vfedorenko.mvistarter.domain.BooksInteractor
import by.vfedorenko.mvistarter.ui.home.books.BooksIntent
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
import javax.inject.Inject

class ObserveFavoritesMiddleware @Inject constructor(
    private val booksInteractor: BooksInteractor
) : Middleware<FavoritesState> {

    override fun execute(
        intent: MviIntent,
        state: FavoritesState,
        outputIntents: MutableSharedFlow<MviIntent>,
        coroutineScope: CoroutineScope
    ) {
        intent.tryCast<GenericIntent.Init> {
            booksInteractor.observeBooks()
                .filterNot { it.isEmpty() }
                .onEach { it.filter { book -> book.isFavorite } }
                .onEach { outputIntents.tryEmit(BooksIntent.BooksReady(it)) }
                .catch { outputIntents.tryEmit(GenericIntent.Failure(it)) }
                .launchIn(coroutineScope)
        }
    }
}

class SelectBookMiddleware @Inject constructor(
    private val navigationManager: NavigationManager
) : Middleware<FavoritesState> {

    override fun execute(
        intent: MviIntent,
        state: FavoritesState,
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
) : Middleware<FavoritesState> {

    override fun execute(
        intent: MviIntent,
        state: FavoritesState,
        outputIntents: MutableSharedFlow<MviIntent>,
        coroutineScope: CoroutineScope
    ) {
        intent.tryCast<BooksIntent.OnFavoriteClick> {
            booksInteractor.favoriteBook(book.id, !book.isFavorite)
        }
    }
}
