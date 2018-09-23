package com.examples.kotlincoroutines

import Presenter.WeatherAppPresenter
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import model.CityWeather
import remote.Interceptor
import remote.WeatherService

class WeatherAppActivity : AppCompatActivity(), WeatherAppView {


    private lateinit var selectCity: EditText
    private lateinit var showInfoRecyclerView: RecyclerView
    private lateinit var imageViewSearch: ImageView
    private lateinit var relativeWeatherInfoLayout: RelativeLayout
    private lateinit var weatherAppPresenter: WeatherAppPresenter
    private lateinit var cityWeatherAdapter: CityWeatherAdapter
    private lateinit var weatherService: WeatherService
    private lateinit var interceptor: Interceptor
    private var apiKey = BuildConfig.OPENWEATHERMAP_API_KEY

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weatherapp)
        initViews()
    }

    private fun initViews() {
        selectCity = findViewById(R.id.edittext_city_name)
        //showInfoRecyclerView = findViewById(R.id.city_recyclerview)
        imageViewSearch = findViewById(R.id.image_search)
        relativeWeatherInfoLayout = findViewById(R.id.relativelayout_weatherinfo)
        interceptor = Interceptor()
        weatherService = interceptor.getClient().create(WeatherService::class.java)
        weatherAppPresenter = WeatherAppPresenter()

        imageViewSearch.setOnClickListener {
            val cityName = selectCity.text.toString()
            weatherAppPresenter.getWeatherOfCity(cityName, apiKey, weatherService)
        }
    }

    override fun showServerError(message: String) {
        Log.e("server error", message)
    }

    override fun showWeatherInfo(cityWeather: CityWeather) {
        hideKeyboard()
        setAdapter(cityWeather)
    }

    private fun setAdapter(cityWeather: CityWeather) {
        cityWeatherAdapter = CityWeatherAdapter(this, cityWeather)
        showInfoRecyclerView?.setLayoutManager(GridLayoutManager(this, 2))
        val itemOffsetDecoration = ItemOffsetDecoration(this, R.dimen.item_offset)
        showInfoRecyclerView?.addItemDecoration(itemOffsetDecoration)
        showInfoRecyclerView?.setAdapter(this.cityWeatherAdapter)
    }

    @SuppressLint("WrongConstant")
    private fun hideKeyboard() {
        val imm = this.getSystemService("input_method") as InputMethodManager
        imm.hideSoftInputFromWindow(this.window.decorView.windowToken, 0)
    }
}
