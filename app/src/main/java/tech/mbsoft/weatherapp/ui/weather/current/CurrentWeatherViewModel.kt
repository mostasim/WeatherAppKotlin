package tech.mbsoft.weatherapp.ui.weather.current

import androidx.lifecycle.ViewModel;
import tech.mbsoft.weatherapp.data.provider.UnitProvider
import tech.mbsoft.weatherapp.data.repository.ForecastRepository
import tech.mbsoft.weatherapp.internal.UnitSystem
import tech.mbsoft.weatherapp.internal.lazyDeffered

class CurrentWeatherViewModel(
        private val forecastRepository : ForecastRepository,
        unitProvider : UnitProvider
) : ViewModel() {
    private val unitSystem =unitProvider.getUnitSystem()

    val isMetric : Boolean
        get() = unitSystem == UnitSystem.METRIC

    val weather by lazyDeffered {
        forecastRepository.getCurrentWeather(isMetric)
    }

    val weatherLocation by lazyDeffered{
        forecastRepository.getWeatherLocation()
    }
}
