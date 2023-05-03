package by.vfedorenko.mvistarter.ui.home.books

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import by.vfedorenko.mvistarter.R
import by.vfedorenko.mvistarter.data.entities.Book
import by.vfedorenko.mvistarter.ui.compose.theme.MVIStarterTheme
import by.vfedorenko.mvistarter.ui.compose.widgets.ShimmeryContent
import by.vfedorenko.starter.MviIntent

@Composable
fun BooksScreen(viewModel: BooksViewModel) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    BooksContent(
        modifier = Modifier.fillMaxSize(),
        state = state,
        onIntent = viewModel::acceptIntent
    )
}

@Composable
fun BooksList(
    books: List<Book>,
    onItemClick: (Book) -> Unit,
    onFavoriteClick: (Book) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(MVIStarterTheme.dimensions.grid04)
    ) {
        items(books) {
            BooksItem(
                book = it,
                onClick = { onItemClick(it) },
                onFavorite = { onFavoriteClick(it) }
            )

            Spacer(modifier = Modifier.size(MVIStarterTheme.dimensions.grid04))
        }
    }
}

@Composable
private fun BooksContent(
    state: BooksState,
    onIntent: (MviIntent) -> Unit,
    modifier: Modifier = Modifier
) {
    BooksList(
        modifier = modifier,
        books = state.books,
        onItemClick = { onIntent(BooksIntent.OnBookClick(it)) },
        onFavoriteClick = { onIntent(BooksIntent.OnFavoriteClick(it)) }
    )
}

@Composable
private fun BooksItem(
    book: Book,
    onClick: () -> Unit,
    onFavorite: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        ShimmeryContent(
            shimmerVisible = book == Book.empty
        ) {
            Row(modifier = Modifier.padding(MVIStarterTheme.dimensions.grid04)) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = book.title,
                        style = MaterialTheme.typography.headlineMedium
                    )

                    Spacer(modifier = Modifier.size(MVIStarterTheme.dimensions.grid04))

                    Text(
                        text = book.body,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                IconButton(
                    onClick = onFavorite
                ) {
                    val res = if (book.isFavorite) R.drawable.ic_favorite_on else R.drawable.ic_favorite_off
                    Icon(
                        painter = painterResource(res),
                        contentDescription = "FAVORITE_BUTTON",
                        tint = Color.Red.copy(alpha = 0.8f)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BooksContentPreview() {
    BooksContent(BooksState(), {})
}
