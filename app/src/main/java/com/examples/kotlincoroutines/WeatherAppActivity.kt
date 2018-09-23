package com.examples.kotlincoroutines

import Coroutines.DefaultAsyncTasksManager
import Presenter.LoadedCityWeather
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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import remote.Interceptor
import remote.WeatherService

class WeatherAppActivity : AppCompatActivity(), WeatherAppView {

    private lateinit var selectCity: EditText
    private lateinit var imageViewSearch: ImageView
    private lateinit var relativeWeatherInfoLayout: RelativeLayout
    private lateinit var weatherAppPresenter: WeatherAppPresenter
    private lateinit var cityWeatherAdapter: CityWeatherAdapter
    private lateinit var weatherService: WeatherService
    private lateinit var interceptor: Interceptor
    private lateinit var defaultAsyncTasManager: DefaultAsyncTasksManager
    private lateinit var showInfoRecyclerView: RecyclerView
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weatherapp)
        initViews()
    }

    private fun initViews() {
        selectCity = findViewById(R.id.edittext_city_name)
        imageViewSearch = findViewById(R.id.image_search)
        relativeWeatherInfoLayout = findViewById(R.id.relativelayout_weatherinfo)
        showInfoRecyclerView = findViewById<RecyclerView>(R.id.my_recycler_view)
        viewManager = LinearLayoutManager(this)
        interceptor = Interceptor()
        weatherService = interceptor.getClient().create(WeatherService::class.java)
        defaultAsyncTasManager = DefaultAsyncTasksManager()
        weatherAppPresenter = WeatherAppPresenter(this, defaultAsyncTasManager)

        imageViewSearch.setOnClickListener {

            weatherAppPresenter.getWeatherOfCity(weatherService)
        }
    }

    override fun showServerError(message: String) {
        Log.e("server error", message)
    }

    override fun showWeatherInfo(cityWeather: LoadedCityWeather) {
        hideKeyboard()
        setAdapter(cityWeather)
    }

    private fun setAdapter(cityWeather: LoadedCityWeather) {
        cityWeatherAdapter = CityWeatherAdapter(this, cityWeather)
        showInfoRecyclerView.setLayoutManager(GridLayoutManager(this, 2))
        val itemOffsetDecoration = ItemOffsetDecoration(this, R.dimen.item_offset)
        showInfoRecyclerView.addItemDecoration(itemOffsetDecoration)
        showInfoRecyclerView.setAdapter(this.cityWeatherAdapter)
    }

    @SuppressLint("WrongConstant")
    private fun hideKeyboard() {
        val imm = this.getSystemService("input_method") as InputMethodManager
        imm.hideSoftInputFromWindow(this.window.decorView.windowToken, 0)
    }
}
