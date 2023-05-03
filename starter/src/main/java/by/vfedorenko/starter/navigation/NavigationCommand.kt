package by.vfedorenko.starter.navigation

import by.vfedorenko.starter.compose.Dialog
import by.vfedorenko.starter.genericErrorDialog

sealed class NavigationCommand(val directions: NavigationDirections = NavigationDirections.Stub)

class Forward(directions: NavigationDirections) : NavigationCommand(directions)
class CleanForward(directions: NavigationDirections) : NavigationCommand(directions)
class Replace(directions: NavigationDirections) : NavigationCommand(directions)
object Back : NavigationCommand()
class BackTo(directions: NavigationDirections) : NavigationCommand(directions)

// Custom
data class OpenAlertDialog(
    val dialog: Dialog = genericErrorDialog,
    val onPositiveButtonClick: () -> Unit = {},
    val onNegativeButtonClick: () -> Unit = {},
    val onDismiss: () -> Unit = {}
) : NavigationCommand()
data class OpenBrowser(val url: String) : NavigationCommand()
