package tech.mbsoft.weatherapp.data.provider

import tech.mbsoft.weatherapp.data.db.entity.WeatherLocation

/*******
Created by Mostasim on 09-Jan-19
Md Mostasim Billah
mostasim.10@gmail.com
mbSoft
 *******/
interface LocationProvider {
    suspend fun hasLocationChanged(lastWeatherLocation: WeatherLocation) : Boolean
    suspend fun getPreferredLocationString() : String
}