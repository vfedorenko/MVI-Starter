package by.vfedorenko.mvistarter.ui.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import by.vfedorenko.mvistarter.R

enum class BottomNavigationItem(
    val route: String,
    @DrawableRes val icon: Int,
    @StringRes val title: Int
) {
    Library("library", R.drawable.ic_home, R.string.library),
    Favorites("favorites", R.drawable.ic_favorite_on, R.string.favorites),
    Settings("settings", R.drawable.ic_settings, R.string.settings);

    companion object {
        fun getByRoute(route: String) = values().firstOrNull { it.route == route } ?: Library
    }
}

@Composable
fun HomeBottomNavigation(navController: NavHostController) {
    NavigationBar {
        BottomNavigationItem.values().forEach { item ->
            val title = stringResource(item.title)
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination

            NavigationBarItem(
                icon = { Icon(painterResource(item.icon), contentDescription = title) },
                label = {
                    Text(
                        text = title,
                        maxLines = 1,
                        softWrap = true
                    )
                },
                selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}
