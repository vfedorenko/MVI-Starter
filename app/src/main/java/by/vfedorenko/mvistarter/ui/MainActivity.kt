package by.vfedorenko.mvistarter.ui

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.rememberNavController
import by.vfedorenko.mvistarter.ui.navigation.AppNavHost
import by.vfedorenko.starter.navigation.NavigationDirections
import by.vfedorenko.starter.compose.AppDialog
import by.vfedorenko.starter.navigation.Back
import by.vfedorenko.starter.navigation.BackTo
import by.vfedorenko.starter.navigation.CleanForward
import by.vfedorenko.starter.navigation.Forward
import by.vfedorenko.starter.navigation.NavigationManager
import by.vfedorenko.starter.navigation.OpenAlertDialog
import by.vfedorenko.starter.navigation.OpenBrowser
import by.vfedorenko.starter.navigation.Replace
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber
import javax.inject.Inject

@ExperimentalAnimationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigationManager: NavigationManager

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                val navController = rememberNavController()

                var dialogCommand by remember { mutableStateOf<OpenAlertDialog?>(null) }
                dialogCommand?.let {
                    AppDialog(
                        dialog = it.dialog,
                        onPositiveButtonClick = {
                            it.onPositiveButtonClick()
                            dialogCommand = null
                        },
                        onNegativeButtonClick = {
                            it.onNegativeButtonClick()
                            dialogCommand = null
                        },
                        onDismiss = it.onDismiss
                    )
                }

                LaunchedEffect(Unit) {
                    navigationManager.commands
                        .onEach { command ->
                            if (navController.currentDestination?.route != command.directions.destination) {
                                when (command) {
                                    is Forward -> navController.navigate(command.directions.destination)

                                    is CleanForward -> {
                                        navController.clearBackStack()
                                        navController.navigate(command.directions.destination) {
                                            navController.clearSelf(this)
                                        }
                                    }

                                    is Replace ->
                                        navController.navigate(command.directions.destination) {
                                            navController.clearSelf(this)
                                        }

                                    Back -> {
                                        if (navController.previousBackStackEntry != null) {
                                            navController.popBackStack()
                                        }
                                    }

                                    is BackTo ->
                                        navController.popBackStack(
                                            route = command.directions.destination,
                                            inclusive = false
                                        )

                                    is OpenAlertDialog -> {
                                        dialogCommand = command
                                    }

                                    is OpenBrowser -> openBrowser(command.url)

                                }
                            }
                        }
                        .catch { Timber.e(it, "Failed to consume navigation command") }
                        .launchIn(this)
                }

                AppNavHost(
                    navController = navController,
                    startDestination = mainViewModel.startDestination
                )
            }
        }
    }

    private fun NavHostController.clearBackStack() {
        while (previousBackStackEntry != null) {
            popBackStack()
        }
    }

    private fun NavHostController.clearSelf(options: NavOptionsBuilder) {
        currentDestination?.route?.let {
            options.popUpTo(it) { inclusive = true }
        }
    }

    private fun openBrowser(url: String) {
        try {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
        } catch (e: ActivityNotFoundException) {
            Timber.e("No Browser app was found on the device")
        }
    }
}

@HiltViewModel
class MainViewModel @Inject constructor(
//    authPrefs: AuthPrefs
) : ViewModel() {
    val startDestination = when {
//        authPrefs.token.isBlank() -> NavigationDirections.Login.destination
//        authPrefs.selectedShopId == 0L -> NavigationDirections.Shops.destination
        else -> NavigationDirections.Login.destination
    }
}
