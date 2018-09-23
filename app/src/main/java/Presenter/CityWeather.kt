package Presenter

sealed class CityWeather
data class LoadedCityWeather(
        val city: Presenter.City,
        val description: String,
        val temperature: Double,
        val icon: String?) : CityWeather()
object LoadingCityWeather : CityWeather()
object UnknownCityWeather : CityWeather()