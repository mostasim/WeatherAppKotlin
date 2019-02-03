package tech.mbsoft.weatherapp.data.provider

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.Preference
import androidx.preference.PreferenceManager
import tech.mbsoft.weatherapp.internal.UnitSystem

const val UNIT_SYTEM="UNIT_SYSTEM"

class UnitProviderImpl(context: Context) : PreferenceProvider(context), UnitProvider {


    override fun getUnitSystem(): UnitSystem {
        val selectedName = preferences.getString(UNIT_SYTEM,UnitSystem.METRIC.name)
        return UnitSystem.valueOf(selectedName!!)

    }
}