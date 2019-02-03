package tech.mbsoft.weatherapp.data.repository

import androidx.lifecycle.LiveData
import tech.mbsoft.weatherapp.data.db.entity.WeatherLocation
import tech.mbsoft.weatherapp.data.db.unitlocalized.UnitSpecificCurrentWeatherEntry

interface ForecastRepository {
    suspend fun getCurrentWeather(metric: Boolean): LiveData<out UnitSpecificCurrentWeatherEntry>
    suspend fun getWeatherLocation() : LiveData<WeatherLocation>
}
