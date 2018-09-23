package com.examples.kotlincoroutines

import Presenter.LoadedCityWeather

interface WeatherAppView {
     fun showServerError(message: String)

     fun showWeatherInfo(cityWeather: LoadedCityWeather)
}
