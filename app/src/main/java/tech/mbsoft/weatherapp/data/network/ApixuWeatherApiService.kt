package tech.mbsoft.weatherapp.data.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import tech.mbsoft.weatherapp.data.network.response.CurrentWeatherResponse


const val API_KEY="22417315fd414e07b94134703183110"

//http://api.apixu.com/v1/current.json?key=22417315fd414e07b94134703183110&q=dhaka

interface ApixuWeatherApiService {

    @GET("current.json")
    fun getCurrentWeather(
            @Query("q") location: String,
            @Query("lang") languageCode: String = "en"
    ): Deferred<CurrentWeatherResponse>

    companion object {
        operator fun invoke(
                connectivityInterceptor: ConnectivityInterceptor
        ): ApixuWeatherApiService {
            val requestInterceptor=Interceptor{chain->
                val url=chain.request()
                        .url()
                        .newBuilder()
                        .addQueryParameter("key", API_KEY)
                        .build()
                val request=chain.request()
                        .newBuilder()
                        .url(url)
                        .build()
                return@Interceptor chain.proceed(request)
            }
            val okHttpClient=OkHttpClient.Builder()
                    .addInterceptor(requestInterceptor)
                    .addInterceptor(connectivityInterceptor)
                    .build()
            return Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl("https://api.apixu.com/v1/")
                    .addCallAdapterFactory(CoroutineCallAdapterFactory())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(ApixuWeatherApiService::class.java)
        }
    }
}