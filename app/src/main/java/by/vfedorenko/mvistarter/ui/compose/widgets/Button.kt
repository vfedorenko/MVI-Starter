package by.vfedorenko.mvistarter.ui.compose.widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp

@Composable
fun PrimaryButtonLarge(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    enabled: Boolean = true,
    text: String,
    testTag: String = "",
    onClick: () -> Unit
) {
    SampleButton(
        text = text,
        testTag = testTag,
        onClick = onClick,
        modifier = modifier,
        isLoading = isLoading,
        enabled = enabled
    )
}

@Composable
fun SampleButton(
    text: String,
    testTag: String,
    isLoading: Boolean,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    Box(contentAlignment = Alignment.Center) {
        Button(
            onClick = onClick,
            modifier = Modifier
                .testTag(testTag)
                .then(modifier),
            enabled = enabled && !isLoading
        ) {
            Text(text = text.uppercase())
        }

        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.size(24.dp),
                strokeWidth = 2.dp
            )
        }
    }
}

@Composable
fun SampleOutlinedButton(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    uppercase: Boolean = true,
    onClick: () -> Unit
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled
    ) {
        val buttonText = if (uppercase) text.uppercase() else text
        Text(text = buttonText)
    }
}
