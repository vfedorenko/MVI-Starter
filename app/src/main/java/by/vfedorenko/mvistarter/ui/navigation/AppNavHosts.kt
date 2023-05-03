package by.vfedorenko.mvistarter.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import by.vfedorenko.mvistarter.ui.bookdetails.BookDetailsScreen
import by.vfedorenko.mvistarter.ui.home.HomeHost
import by.vfedorenko.mvistarter.ui.home.books.BooksScreen
import by.vfedorenko.mvistarter.ui.home.favorites.FavoritesScreen
import by.vfedorenko.mvistarter.ui.home.settings.SettingsScreen
import by.vfedorenko.mvistarter.ui.login.LoginScreen
import by.vfedorenko.starter.navigation.NavigationDirections

@Composable
fun AppNavHost(navController: NavHostController, startDestination: String) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {

        composable(route = NavigationDirections.Login.destination) {
            LoginScreen(hiltViewModel(it))
        }

        composable(route = NavigationDirections.Home.destination) {
            HomeHost()
        }

        composable(
            route = NavigationDirections.BookDetails.ROUTE,
            arguments = NavigationDirections.BookDetails.arguments
        ) {
            it.arguments?.getInt(NavigationDirections.BookDetails.KEY_BOOK_ID)?.let { id ->
                BookDetailsScreen(hiltViewModel(it), navController, id)
            }
        }
    }
}

@Composable
fun BottomNavHost(
    navController: NavHostController,
    startDestination: String,
    modifier: Modifier
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(route = BottomNavigationItem.Library.route) {
            BooksScreen(hiltViewModel(it))
        }

        composable(route = BottomNavigationItem.Favorites.route) {
            FavoritesScreen(hiltViewModel(it))
        }

        composable(route = BottomNavigationItem.Settings.route) {
            SettingsScreen(hiltViewModel(it))
        }
    }
}
