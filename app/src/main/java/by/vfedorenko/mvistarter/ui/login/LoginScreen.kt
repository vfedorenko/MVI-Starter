package by.vfedorenko.mvistarter.ui.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import by.vfedorenko.mvistarter.R
import by.vfedorenko.mvistarter.ui.compose.theme.MVIStarterTheme
import by.vfedorenko.mvistarter.ui.compose.widgets.PrimaryButtonLarge
import by.vfedorenko.starter.MviIntent
import by.vfedorenko.mvistarter.ui.compose.widgets.PasswordField

@Composable
fun LoginScreen(viewModel: LoginViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Login") }
            )
        }
    ) {
        val state by viewModel.state.collectAsStateWithLifecycle()
        LoginContent(
            modifier = Modifier.padding(it),
            state = state,
            onIntent = viewModel::acceptIntent
        )
    }
}

@Composable
private fun LoginContent(
    state: LoginState,
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
        Text(
            modifier = Modifier.padding(MVIStarterTheme.dimensions.grid06),
            text = stringResource(R.string.welcome),
            style = MaterialTheme.typography.headlineMedium
        )

        Input(state, onIntent)
    }
}

@Composable
private fun Input(
    state: LoginState,
    onIntent: (MviIntent) -> Unit
) {
    val focusRequester = remember { FocusRequester() }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = MVIStarterTheme.dimensions.grid02),
        label = { Text(stringResource(R.string.hint_email)) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions(onNext = { focusRequester.requestFocus() }),
        isError = state.error.isNotBlank(),
        value = state.email,
        onValueChange = { onIntent(LoginIntent.EmailChanged(it)) }
    )

    PasswordField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = MVIStarterTheme.dimensions.grid02)
            .focusRequester(focusRequester),
        hint = stringResource(R.string.hint_password),
        error = state.error,
        password = state.password,
        onPasswordChanged = { onIntent(LoginIntent.PasswordChanged(it)) },
        onDoneClicked = { onIntent(LoginIntent.PerformLogin) }
    )

    Spacer(modifier = Modifier.size(MVIStarterTheme.dimensions.grid04))

    PrimaryButtonLarge(
        modifier = Modifier.fillMaxWidth(),
        text = stringResource(R.string.sign_in),
        isLoading = state.isLoading
    ) {
        onIntent(LoginIntent.PerformLogin)
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginContentPreview() {
    LoginContent(LoginState(), {})
}
