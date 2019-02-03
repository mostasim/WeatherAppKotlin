package tech.mbsoft.weatherapp.data.provider

import tech.mbsoft.weatherapp.internal.UnitSystem

/*******
Created by Mostasim on 09-Jan-19
Md Mostasim Billah
mostasim.10@gmail.com
mbSoft
 *******/
interface UnitProvider {
    fun getUnitSystem(): UnitSystem
}