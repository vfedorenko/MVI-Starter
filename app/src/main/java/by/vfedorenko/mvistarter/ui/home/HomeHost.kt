package by.vfedorenko.mvistarter.ui.home

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import by.vfedorenko.mvistarter.ui.navigation.BottomNavHost
import by.vfedorenko.mvistarter.ui.navigation.BottomNavigationItem
import by.vfedorenko.mvistarter.ui.navigation.HomeBottomNavigation

@Composable
fun HomeHost() {
    val navController = rememberNavController()

    val currentRoute by navController.currentBackStackEntryAsState()
    val currentItem = BottomNavigationItem.getByRoute(currentRoute?.destination?.route ?: "")

    Scaffold(
        modifier = Modifier.navigationBarsPadding(),
        topBar = {
            TopAppBar(
                title = { Text(stringResource(currentItem.title)) }
            )
        },
        bottomBar = { HomeBottomNavigation(navController) }
    ) { innerPadding ->
        BottomNavHost(
            navController = navController,
            startDestination = BottomNavigationItem.Library.route,
            modifier = Modifier.padding(innerPadding)
        )
    }
}
