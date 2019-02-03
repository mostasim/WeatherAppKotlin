package tech.mbsoft.weatherapp.data.provider

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.Deferred
import tech.mbsoft.weatherapp.data.db.entity.WeatherLocation
import tech.mbsoft.weatherapp.internal.LocationPermissionNotGrantedException
import tech.mbsoft.weatherapp.internal.asDeffered

const val USE_DEVICE_LOCATION = "USE_DEVICE_LOCATION"
const val CUSTOM_LOCATION = "CUSTOM_LOCATION"

class LocationProviderImpl(
        private val fusedLocationProviderClient: FusedLocationProviderClient,
        context : Context
) : PreferenceProvider(context),LocationProvider {

    private val appContext = context.applicationContext

    override suspend fun hasLocationChanged(lastWeatherLocation: WeatherLocation): Boolean {
        val deviceLocationChanged = try {
            hasDeviceLocationChanged(lastWeatherLocation)
        }catch (e: LocationPermissionNotGrantedException)
        {
            false
        }

        return deviceLocationChanged || hasCustomLocatinChanged(lastWeatherLocation)
    }

    private fun hasCustomLocatinChanged(lastWeatherLocation: WeatherLocation): Boolean {
        val customLocationName = getCustomlocationName()
        return customLocationName != lastWeatherLocation.name
    }



    private suspend fun hasDeviceLocationChanged(lastWeatherLocation: WeatherLocation): Boolean {
        if (!isUsingDeviceLocation())
            return false

        val deviceLocation = getlastDeviceLocation().await()
                ?: return false

        val comparisonThreshold = 0.03
        return  Math.abs(deviceLocation.latitude - lastWeatherLocation.lat) > comparisonThreshold &&
                Math.abs(deviceLocation.longitude -  lastWeatherLocation.lon) > comparisonThreshold
    }

    @SuppressLint("MissingPermission")
    private fun getlastDeviceLocation(): Deferred<Location> {
        return if(hasLocationPermission())
            fusedLocationProviderClient.lastLocation.asDeffered()
        else
            throw LocationPermissionNotGrantedException()
    }

    private fun getCustomlocationName(): String? {
        return preferences.getString(CUSTOM_LOCATION,null)
    }

    private fun isUsingDeviceLocation(): Boolean {
        return preferences.getBoolean(USE_DEVICE_LOCATION,true)
    }

    override suspend fun getPreferredLocationString(): String {
        if(isUsingDeviceLocation()) {
            try {
                val deviceLocation = getlastDeviceLocation().await()
                ?: return "${getCustomlocationName()}"
                return "${deviceLocation.latitude},${deviceLocation.longitude}"
            }catch (e: LocationPermissionNotGrantedException){
                return "${getCustomlocationName()}"
            }

        }else return "${getCustomlocationName()}"
    }
    private fun hasLocationPermission() : Boolean {
        return  ContextCompat.checkSelfPermission(appContext,
                android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
    }
}