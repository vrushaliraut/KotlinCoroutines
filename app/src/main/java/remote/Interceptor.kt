package remote

import com.examples.kotlincoroutines.BuildConfig
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

public class Interceptor {

    fun getClient(): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val clientok = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.OPENWEATHERMAP_URL)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(StringConverterFactory())
                .client(clientok)
                .build()

        return retrofit
    }
}
