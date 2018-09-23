package Presenter

import Coroutines.DefaultAsyncTasksManager
import com.examples.kotlincoroutines.BuildConfig
import domain.BaseUseCase
import kotlinx.coroutines.experimental.Deferred
import model.CityWeather
import remote.WeatherService

class GetWeatherUseCase constructor(asyncTasksManager: DefaultAsyncTasksManager, private val weatherRepository: WeatherService) :
        BaseUseCase(asyncTasksManager) {
    class GetWeatherException (val cityAndCountry: Presenter.City) : RuntimeException()

    suspend fun execute(city: Presenter.City): LoadedCityWeather {
        val weather: Deferred<CityWeather?> = asyncAwait {
            weatherRepository.getCurrentWeather(city.cityAndCountry,BuildConfig.OPENWEATHERMAP_API_KEY)
        }
        return mapCurrentWeatherToCityWeather(weather, city)
    }
    private fun mapCurrentWeatherToCityWeather(weather: Deferred<CityWeather?>, city: City): LoadedCityWeather {
        return LoadedCityWeather(
                city,
                "sss" ?: throw GetWeatherException(city),
                11.0?: throw GetWeatherException(city),
                "icon")
    }
}

