package by.vfedorenko.starter.navigation

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NavigationManager @Inject constructor() {

    private val commandsChannel = Channel<NavigationCommand>(Channel.BUFFERED)
    val commands = commandsChannel.receiveAsFlow()

    fun navigate(command: NavigationCommand) {
        commandsChannel.trySend(command)
    }
}
