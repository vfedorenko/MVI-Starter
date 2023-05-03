package by.vfedorenko.starter.compose

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

data class Dialog(
    @StringRes val titleRes: Int? = null,
    @StringRes val textRes: Int? = null,
    @StringRes val positiveButtonTextRes: Int? = null,
    @StringRes val negativeButtonTextRes: Int? = null,
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppDialog(
    dialog: Dialog,
    onPositiveButtonClick: () -> Unit,
    onNegativeButtonClick: () -> Unit,
    onDismiss: () -> Unit
) {
    val dialogTitle = dialog.titleRes?.let { stringResource(id = it) }
    val dialogText = dialog.textRes?.let { stringResource(id = it) }
    val positiveButton = dialog.positiveButtonTextRes?.let { stringResource(id = it) }
    val negativeButton = dialog.negativeButtonTextRes?.let { stringResource(id = it) }

    AlertDialog(onDismissRequest = onDismiss) {
        Column(Modifier.fillMaxWidth()) {
            dialogTitle?.let {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = dialogTitle,
                    style = MaterialTheme.typography.headlineMedium,
                    textAlign = TextAlign.Center,
                )
            }

            dialogText?.let {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = dialogText,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center,
                )
            }

            val dividerColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)

            if (dialogText.isNullOrEmpty()) {
                Divider(
                    modifier = Modifier.padding(top = 24.dp),
                    color = dividerColor
                )
            } else {
                Divider(color = dividerColor)
            }

            Row(modifier = Modifier.height(IntrinsicSize.Min)) {
                val buttonPadding = 16.dp

                negativeButton?.let {
                    // Horizontal divider hack

                    TextButton(
                        onClick = onNegativeButtonClick,
                        modifier = Modifier
                            .weight(1f, true)
                            .padding(buttonPadding)
                    ) {
                        Text(text = negativeButton)
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(1.dp)
                            .background(dividerColor)
                    )
                }

                positiveButton?.let {
                    TextButton(
                        onClick = onPositiveButtonClick,
                        modifier = Modifier
                            .weight(1f, true)
                            .padding(buttonPadding)
                    ) {
                        Text(text = positiveButton)
                    }
                }
            }
        }
    }
}
