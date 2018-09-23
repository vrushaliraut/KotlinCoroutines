package remote

import kotlinx.coroutines.experimental.Deferred
import model.CityWeather
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("weather")
    fun getCurrentWeather(@Query("q") cityName: String,
                          @Query("appid") appId: String)
            : Deferred<CityWeather?>
}
