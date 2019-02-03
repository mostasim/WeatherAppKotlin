package tech.mbsoft.weatherapp.ui.weather.current

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import tech.mbsoft.weatherapp.data.provider.UnitProvider
import tech.mbsoft.weatherapp.data.repository.ForecastRepository

/*******
Created by Mostasim on 31-Dec-18
Md Mostasim Billah
mostasim.10@gmail.com
mbSoft
 *******/

class CurrentWeatherViewModelFactory(
        private val forecastRepository: ForecastRepository,
        private val unitProvider : UnitProvider
) : ViewModelProvider.NewInstanceFactory(){

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CurrentWeatherViewModel(forecastRepository, unitProvider) as T
    }
}