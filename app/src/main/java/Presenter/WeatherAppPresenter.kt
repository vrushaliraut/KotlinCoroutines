package Presenter

import Coroutines.CoroutineManager
import Coroutines.DefaultAsyncTasksManager
import com.examples.kotlincoroutines.WeatherAppView
import kotlinx.coroutines.experimental.CoroutineScope
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import remote.WeatherService
import java.util.*

class WeatherAppPresenter(weatherAppView: WeatherAppView, private val asyncTasManager: DefaultAsyncTasksManager) : CoroutineManager {

    private val coroutinesJobs: MutableList<Job> = mutableListOf()
    private val citiesWeather: MutableList<CityWeather> = mutableListOf()

    var wheatherappview: WeatherAppView = weatherAppView

    internal lateinit var getWeatherUseCase: GetWeatherUseCase

    companion object {
        private val CITIES: List<City> = listOf(
                City("London", "uk"),
                City("Venice", "it"),
                City("New York", "us"))
    }

    init {
        initCitiesWeather()

    }

    private fun initCitiesWeather() {
        CITIES.forEach { citiesWeather.add(UnknownCityWeather) }
    }

    override fun launchOnUI(block: suspend CoroutineScope.() -> Unit) {
        val job: Job = launch(UI) { block() }
        coroutinesJobs.add(job)
        job.invokeOnCompletion { coroutinesJobs.remove(job) }
    }

    fun getWeatherOfCity(weatherService: WeatherService) {
        getWeatherUseCase = GetWeatherUseCase(asyncTasksManager = asyncTasManager, weatherRepository = weatherService)
        launchOnUI({
            wheatherappview.showWeatherInfo(getWeatherUseCase.execute(CITIES[1]))
        })
    }

    private suspend fun simulateSlowNetwork() {
        // Random delay used to simulate a slow network connection
        delay(1000 + Random().nextInt(4000).toLong())
    }


}

