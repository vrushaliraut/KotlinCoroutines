package com.examples.kotlincoroutines

import model.CityWeather

interface WeatherAppView {
     fun showServerError(message: String)

     fun showWeatherInfo(cityWeather: CityWeather)
}
