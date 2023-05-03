package by.vfedorenko.mvistarter.ui.compose.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import by.vfedorenko.mvistarter.R
import by.vfedorenko.mvistarter.ui.compose.theme.MVIStarterTheme

const val PASSWORD_INPUT = "PASSWORD_INPUT"

@Composable
fun PasswordField(
    modifier: Modifier = Modifier,
    hint: String = "",
    error: String = "",
    password: String = "",
    onPasswordChanged: (String) -> Unit = {},
    onDoneClicked: KeyboardActionScope.() -> Unit = {}
) {
    Column(modifier = modifier) {
        var passwordVisibility by remember { mutableStateOf(false) }

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth()
                .testTag(PASSWORD_INPUT),
            singleLine = true,
            isError = error.isNotBlank(),
            visualTransformation = if (passwordVisibility) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = onDoneClicked),
            trailingIcon = {
                IconButton(
                    onClick = {
                        passwordVisibility = !passwordVisibility
                    }
                ) {
                    Icon(
                        painter = if (passwordVisibility) {
                            painterResource(R.drawable.ic_visibility_off)
                        } else {
                            painterResource(R.drawable.ic_visibility_on)
                        },
                        contentDescription = "Change password visibility"
                    )
                }
            },
            value = password,
            onValueChange = onPasswordChanged,
            label = { Text(hint) }
        )

        if (error.isNotBlank()) {
            ErrorText(error)
        }
    }
}

@Composable
fun ErrorText(text: String) {
    Text(
        modifier = Modifier.padding(top = MVIStarterTheme.dimensions.grid02, start = MVIStarterTheme.dimensions.grid01),
        text = text,
        style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.error)
    )
}
