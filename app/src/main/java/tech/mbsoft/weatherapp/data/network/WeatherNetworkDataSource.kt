package tech.mbsoft.weatherapp.data.network

import androidx.lifecycle.LiveData
import tech.mbsoft.weatherapp.data.network.response.CurrentWeatherResponse

interface WeatherNetworkDataSource {
    val downloadCurrentWeather : LiveData<CurrentWeatherResponse>

    suspend fun fetchCurrentWeather(
            location : String,
            languageCode: String
    )
}