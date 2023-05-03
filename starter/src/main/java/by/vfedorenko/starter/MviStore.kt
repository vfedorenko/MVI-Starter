package by.vfedorenko.starter

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onSubscription
import kotlinx.coroutines.flow.update
import timber.log.Timber

interface MviIntent
interface MviState

sealed interface GenericIntent : MviIntent {
    object Init : GenericIntent
    object Loading : GenericIntent
    data class Failure(
        val error: Throwable,
        val showAlert: Boolean = true
    ) : GenericIntent
}

interface Reducer<State : MviState> {
    fun reduce(state: State, intent: MviIntent): State
}

interface Middleware<State : MviState> {
    fun execute(
        intent: MviIntent,
        state: State,
        outputIntents: MutableSharedFlow<MviIntent>,
        coroutineScope: CoroutineScope
    )
}

inline fun <reified T> Any?.tryCast(block: T.() -> Unit) {
    if (this is T) block()
}

class MviStore<State : MviState>(
    private val initialState: State,
    private val reducer: Reducer<State>,
    private val middlewares: Set<Middleware<State>>,
    private val commonMiddlewares: Set<Middleware<MviState>>
) {

    val state = MutableStateFlow(initialState)
    val intents = MutableSharedFlow<MviIntent>(extraBufferCapacity = 100)

    fun init(coroutineScope: CoroutineScope) {
        intents
            .onSubscription { intents.emit(GenericIntent.Init) }
            .onEach {
                state.update { old ->
                    reducer.reduce(old, it)
                }
            }
            .onEach { intent ->
                Timber.d("ACTION: ${intent.javaClass.simpleName}")
                middlewares.forEach {
                    it.execute(intent, state.value, intents, coroutineScope)
                }

                commonMiddlewares.forEach {
                    it.execute(intent, state.value, intents, coroutineScope)
                }
            }
            .onCompletion {
                Timber.d("Clear the STATE: ${initialState.javaClass.simpleName}")
                state.value = initialState
            }
            .launchIn(coroutineScope)
    }
}
