package tech.mbsoft.weatherapp.data.network.response

import com.google.gson.annotations.SerializedName
import tech.mbsoft.weatherapp.data.db.entity.CurrentWeatherEntry
import tech.mbsoft.weatherapp.data.db.entity.WeatherLocation

data class CurrentWeatherResponse(
        val location: WeatherLocation,
        @SerializedName("current")
        val currentWeatherEntry: CurrentWeatherEntry
)