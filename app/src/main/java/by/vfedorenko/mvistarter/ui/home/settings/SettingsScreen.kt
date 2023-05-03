package by.vfedorenko.mvistarter.ui.home.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import by.vfedorenko.mvistarter.ui.compose.theme.MVIStarterTheme
import by.vfedorenko.mvistarter.ui.compose.widgets.PrimaryButtonLarge
import by.vfedorenko.starter.MviIntent

@Composable
fun SettingsScreen(viewModel: SettingsViewModel) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    SettingsContent(
        state = state,
        onIntent = viewModel::acceptIntent
    )
}

@Composable
private fun SettingsContent(
    state: SettingsState,
    onIntent: (MviIntent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(MVIStarterTheme.dimensions.grid04),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        PrimaryButtonLarge(
            text = "Logout",
            modifier = Modifier.fillMaxWidth(),
        ) {
            onIntent(SettingsIntent.Logout)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SettingsContentPreview() {
    SettingsContent(SettingsState(), {})
}
