package tech.mbsoft.weatherapp.data.network.response

import com.google.gson.annotations.SerializedName
import tech.mbsoft.weatherapp.data.db.entity.CurrentWeatherEntry
import tech.mbsoft.weatherapp.data.db.entity.Location

data class CurrentWeatherResponse(
        val location: Location,
        @SerializedName("current")
        val currentWeatherEntry: CurrentWeatherEntry
)