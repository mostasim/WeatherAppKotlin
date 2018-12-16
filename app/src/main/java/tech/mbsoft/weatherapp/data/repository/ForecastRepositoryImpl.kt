package tech.mbsoft.weatherapp.data.repository

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.ZonedDateTime
import tech.mbsoft.weatherapp.data.db.CurrentWeatherDao
import tech.mbsoft.weatherapp.data.db.unitlocalized.UnitSpecificCurrentWeatherEntry
import tech.mbsoft.weatherapp.data.network.WeatherNetworkDataSource
import tech.mbsoft.weatherapp.data.network.response.CurrentWeatherResponse
import java.util.*

class ForecastRepositoryImpl(
        private val currentWeatherDao: CurrentWeatherDao,
        private val weatherNetworkDataSource: WeatherNetworkDataSource
) : ForecastRepository {

    init {
        weatherNetworkDataSource.downloadCurrentWeather.observeForever{newCurrentWeather ->
            //persist
            persistFetchedCurrentWeather(newCurrentWeather)

        }
    }
    override suspend fun getCurrentWeather(metric: Boolean): LiveData<out UnitSpecificCurrentWeatherEntry> {
        return withContext(Dispatchers.IO){
            initWeatherData()
            return@withContext if(metric) currentWeatherDao.getWeatherMetric()
            else currentWeatherDao.getWeatherImperial()
        }
    }

    private fun persistFetchedCurrentWeather(feathedWeather: CurrentWeatherResponse){
        GlobalScope.launch(Dispatchers.IO) {
            currentWeatherDao.upsert(feathedWeather.currentWeatherEntry)
        }
    }
    private suspend fun initWeatherData() {
        if (isFetchCurrentNeeded(ZonedDateTime.now().minusHours(1)))
            fetchCurrentWeather()
    }

    private suspend fun fetchCurrentWeather(){
        weatherNetworkDataSource.fetchCurrentWeather(
                "Dhaka",
                Locale.getDefault().language
        )
    }

    private fun isFetchCurrentNeeded(lastFetchTime: ZonedDateTime): Boolean {
        val thirtyMinutesAgo= ZonedDateTime.now().minusMinutes(30)
        return lastFetchTime.isBefore(thirtyMinutesAgo)
    }
}
