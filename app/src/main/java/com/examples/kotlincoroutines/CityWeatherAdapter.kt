package com.examples.kotlincoroutines

import Presenter.LoadedCityWeather
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CityWeatherAdapter(weatherAppActivity: WeatherAppActivity, cityWeather: LoadedCityWeather) :
        RecyclerView.Adapter<CityWeatherAdapter.CityWeatherViewHolder>() {

    private var cityWeather: LoadedCityWeather = cityWeather
    private var context: Context = weatherAppActivity

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityWeatherViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_city_weather_card, null)

        return CityWeatherViewHolder(view)
    }

    override fun onBindViewHolder(holder: CityWeatherViewHolder, position: Int) {

        holder.cityNameTextView.text = "name"

        var weatherInCelsius = " - "

        holder.cityWeatherDegreesTextView.text = weatherInCelsius
        holder.cityWeatherDescriptionTextView.text = "description"
        holder.cityWeatherIconImageView.setImageResource(R.drawable.art_snow)
        holder.cityWeatherFavoriteImageView.setImageResource(R.drawable.ic_favorite_border_white_36dp)

    }

    override fun getItemCount(): Int {
        return 1
    }

    inner class CityWeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var cityNameTextView: TextView
        var cityWeatherDegreesTextView: TextView
        var cityWeatherDescriptionTextView: TextView
        var cityWeatherIconImageView: ImageView
        var cityWeatherFavoriteImageView: ImageView


        init {
            this.cityNameTextView = itemView.findViewById(R.id.city_name_textview)
            this.cityWeatherDegreesTextView = itemView.findViewById(R.id.city_weather_degrees_textview)
            this.cityWeatherDescriptionTextView = itemView.findViewById(R.id.city_weather_description_textview)
            this.cityWeatherIconImageView = itemView.findViewById(R.id.city_weather_icon_imageview)
            this.cityWeatherFavoriteImageView = itemView.findViewById(R.id.city_weather_favorite_imageview)
        }
    }
}

fun getArtResourceForWeatherCondition(weatherId: Int): Int {
    if (weatherId >= 200 && weatherId <= 232) {
        return R.drawable.art_storm
    } else if (weatherId >= 300 && weatherId <= 321) {
        return R.drawable.art_light_rain
    } else if (weatherId >= 500 && weatherId <= 504) {
        return R.drawable.art_rain
    } else if (weatherId == 511) {
        return R.drawable.art_snow
    } else if (weatherId >= 520 && weatherId <= 531) {
        return R.drawable.art_rain
    } else if (weatherId >= 600 && weatherId <= 622) {
        return R.drawable.art_snow
    } else if (weatherId >= 701 && weatherId <= 761) {
        return R.drawable.art_fog
    } else if (weatherId == 761 || weatherId == 781) {
        return R.drawable.art_storm
    } else if (weatherId == 800) {
        return R.drawable.art_clear
    } else if (weatherId == 801) {
        return R.drawable.art_light_clouds
    } else if (weatherId >= 802 && weatherId <= 804) {
        return R.drawable.art_clouds
    }
    return -1
}

fun convertToCelsius(kelvin: Double): Double {

    return kelvin - 273
}

fun getDegreesRepresentation(context: Context, temperature: Double): String {
    return String.format(context.getString(R.string.format_temperature), temperature)
}
