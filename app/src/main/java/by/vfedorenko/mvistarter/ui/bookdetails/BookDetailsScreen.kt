package by.vfedorenko.mvistarter.ui.bookdetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import by.vfedorenko.mvistarter.R
import by.vfedorenko.mvistarter.ui.compose.theme.MVIStarterTheme
import by.vfedorenko.starter.MviIntent

@Composable
fun BookDetailsScreen(
    viewModel: BookDetailsViewModel,
    navController: NavHostController,
    bookId: Int
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    viewModel.acceptIntent(BookDetailsIntent.GetBook(bookId))

    val iconRes = if (state.book.isFavorite) R.drawable.ic_favorite_on else R.drawable.ic_favorite_off
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(state.book.title) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back Button"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { viewModel.acceptIntent(BookDetailsIntent.OnFavoriteClick) }) {
                        Icon(
                            painter = painterResource(iconRes),
                            contentDescription = "Favorite Button"
                        )
                    }
                }
            )
        }
    ) {
        BookDetailsContent(
            modifier = Modifier.padding(it),
            state = state,
            onIntent = viewModel::acceptIntent
        )
    }
}

@Composable
private fun BookDetailsContent(
    state: BookDetailsState,
    onIntent: (MviIntent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(MVIStarterTheme.dimensions.grid04)) {
        Text(
            text = state.book.body,
            style = MaterialTheme.typography.headlineMedium
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun BookDetailsContentPreview() {
    BookDetailsContent(BookDetailsState(), {})
}
