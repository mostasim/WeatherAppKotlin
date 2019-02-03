package tech.mbsoft.weatherapp.data.provider

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

/*******
Created by Mostasim on 09-Jan-19
Md Mostasim Billah
mostasim.10@gmail.com
mbSoft
 *******/
abstract class PreferenceProvider(context : Context) {
    private val appContext = context.applicationContext

    protected val preferences : SharedPreferences
        get() = PreferenceManager.getDefaultSharedPreferences(appContext)
}