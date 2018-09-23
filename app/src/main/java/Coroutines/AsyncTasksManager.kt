
package Coroutines

import kotlinx.coroutines.experimental.CoroutineScope
import kotlinx.coroutines.experimental.Deferred
import model.CityWeather

interface AsyncTasksManager {

    suspend fun <T> async(block: suspend CoroutineScope.() -> T): Deferred<T>

    suspend fun <T> asyncAwait(block: suspend CoroutineScope.() -> T): T

    fun cancelAllAsync()

    fun cleanup()
}