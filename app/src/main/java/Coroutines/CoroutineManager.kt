package Coroutines

import kotlinx.coroutines.experimental.CoroutineScope

interface CoroutineManager {
    fun launchOnUI(block: suspend CoroutineScope.() -> Unit)
}
