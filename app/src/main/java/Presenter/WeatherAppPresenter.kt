package Presenter

import Coroutines.CoroutineManager
import kotlinx.coroutines.experimental.CoroutineScope
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import remote.WeatherService
import java.util.*

class WeatherAppPresenter : CoroutineManager {

    private val coroutinesJobs: MutableList<Job> = mutableListOf()
    override fun launchOnUI(block: suspend CoroutineScope.() -> Unit) {
        val job: Job = launch(UI) { block() }
        coroutinesJobs.add(job)
        job.invokeOnCompletion { coroutinesJobs.remove(job) }
    }

    fun getWeatherOfCity(cityName: String, apiKey: String, weatherService: WeatherService) {
        launchOnUI({
            getCurrentWeather(cityName, apiKey,weatherService)
        })
    }

    private fun getCurrentWeather(city: String, apiKey: String, weatherService: WeatherService): Any {

        return weatherService.getCurrentWeather("Pune", apiKey)
    }

    private suspend fun simulateSlowNetwork() {
        // Random delay used to simulate a slow network connection
        delay(1000 + Random().nextInt(4000).toLong())
    }


}

