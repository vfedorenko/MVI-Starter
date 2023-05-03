package by.vfedorenko.starter.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class NavigationDirections(
    val destination: String = ""
) {
    object Stub : NavigationDirections()
    object Login : NavigationDirections(destination = "login")
    object Home : NavigationDirections(destination = "home")

    class BookDetails(bookId: Int) : NavigationDirections(destination = "book/$bookId") {
        companion object {
            const val KEY_BOOK_ID = "bookId"
            const val ROUTE = "book/{$KEY_BOOK_ID}"
            val arguments = listOf(
                navArgument(KEY_BOOK_ID) { type = NavType.IntType }
            )
        }
    }
}
