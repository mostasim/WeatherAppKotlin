package tech.mbsoft.weatherapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import tech.mbsoft.weatherapp.data.db.entity.WEATHER_LOCATION_ID
import tech.mbsoft.weatherapp.data.db.entity.WeatherLocation

/*******
Created by Mostasim on 09-Jan-19
Md Mostasim Billah
mostasim.10@gmail.com
mbSoft
 *******/

@Dao
interface WeatherLocationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(weatherLocation : WeatherLocation)

    @Query("select * from weather_location where id = $WEATHER_LOCATION_ID")
    fun getLocation() : LiveData<WeatherLocation>
}