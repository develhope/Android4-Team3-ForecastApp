package co.develhope.meteoapp.data

import android.content.Context
import android.content.SharedPreferences
import co.develhope.meteoapp.data.domainmodel.Place
import com.google.gson.Gson

class Preferences(applicationContext: Context) {
    private val preferences: SharedPreferences = applicationContext.getSharedPreferences(
        KEY_FORECAST_APP, Context.MODE_PRIVATE
    )

    val gson = Gson()
    fun setCity(place: Place) {
        val editor = preferences.edit()
        val json = gson.toJson(place)
        editor.putString(KEY_CHOSEN_PLACE, json)
        editor.apply()
    }

    fun getCity(): Place? {
        val json = preferences.getString(KEY_CHOSEN_PLACE, null)
        return gson.fromJson(json, Place::class.java)
    }

}

const val KEY_RECENT_SEARCHES = "recentSearches"
const val KEY_FORECAST_APP = "forecastApp"
const val KEY_CHOSEN_PLACE = "chosenPlace"


