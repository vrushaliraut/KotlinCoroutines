package Coroutines

import androidx.annotation.CallSuper
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.CoroutineScope
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.async

open class DefaultAsyncTasksManager : AsyncTasksManager {

    protected val deferredObjects: MutableList<Deferred<*>> = mutableListOf()

    @CallSuper
    @Synchronized
    override suspend fun <T> async(block: suspend CoroutineScope.() -> T): Deferred<T> {
        val deferred: Deferred<T> = async(CommonPool) { block() }
        deferredObjects.add(deferred)
        deferred.invokeOnCompletion { deferredObjects.remove(deferred) }
        return deferred
    }

    @CallSuper
    @Synchronized
    override suspend fun <T> asyncAwait(block: suspend CoroutineScope.() -> T): T {
        return async(block).await()
    }

    @CallSuper
    @Synchronized
    override fun cancelAllAsync() {
        val deferredObjectsSize = deferredObjects.size

        if (deferredObjectsSize > 0) {
            for (i in deferredObjectsSize - 1 downTo 0) {
                deferredObjects[i].cancel()
            }
        }
    }

    @CallSuper
    @Synchronized
    override fun cleanup() {
        cancelAllAsync()
    }
}