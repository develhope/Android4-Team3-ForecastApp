package co.develhope.meteoapp.ui

import android.content.Context
import android.content.SharedPreferences
import co.develhope.meteoapp.data.domainmodel.Place
import com.google.gson.Gson

class Preferences(applicationContext: Context) {
    private val preferences: SharedPreferences = applicationContext.getSharedPreferences(
        KEY_SEARCHED_CITY, Context.MODE_PRIVATE
    )

    fun setCity(place: Place) {
        val editor = preferences.edit()
        val gson = Gson()
        val json = gson.toJson(place)
        editor.putString(KEY_SEARCHED_CITY, json)
        editor.apply()
    }

    fun getCity(): Place? {
        val gson = Gson()
        val json = preferences.getString(KEY_SEARCHED_CITY, null)
        return gson.fromJson(json, Place::class.java)
    }

}

const val KEY_SEARCHED_CITY = "recent searches"