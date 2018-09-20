package com.examples.kotlincoroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import model.CityWeather

class WeatherAppActivity : AppCompatActivity(), WeatherAppView{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weatherapp)
    }

    override fun showServerError(message: String) {
    }

    override fun showWeatherInfo(cityWeather: CityWeather) {
    }

}
