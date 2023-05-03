package by.vfedorenko.mvistarter.ui.home.favorites

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import by.vfedorenko.mvistarter.ui.home.books.BooksList
import by.vfedorenko.starter.MviIntent

@Composable
fun FavoritesScreen(viewModel: FavoritesViewModel) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    FavoritesContent(
        state = state,
        onIntent = viewModel::acceptIntent
    )
}

@Composable
private fun FavoritesContent(
    state: FavoritesState,
    onIntent: (MviIntent) -> Unit,
    modifier: Modifier = Modifier
) {
    BooksList(
        modifier = modifier,
        books = state.books,
        onItemClick = { onIntent(FavoritesIntent.OnBookClick(it)) },
        onFavoriteClick = { onIntent(FavoritesIntent.OnFavoriteClick(it)) }
    )
}

@Preview(showBackground = true)
@Composable
private fun FavoritesContentPreview() {
    FavoritesContent(FavoritesState(), {})
}
