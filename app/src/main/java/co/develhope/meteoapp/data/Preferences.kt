package co.develhope.meteoapp.data

import android.content.Context
import android.content.SharedPreferences
import co.develhope.meteoapp.data.domainmodel.Place
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.threeten.bp.OffsetDateTime

class Preferences(applicationContext: Context) {
    private val preferences: SharedPreferences = applicationContext.getSharedPreferences(
        KEY_FORECAST_APP, Context.MODE_PRIVATE
    )

    private fun provideGson(): Gson = GsonBuilder()
        .registerTypeAdapter(OffsetDateTime::class.java, OffsetDateTimeTypeAdapter())
        .create()

    fun setCity(place: Place) {
        getCity()?.let { addCityToResentSearches(it) }
        val editor = preferences.edit()
        val json = provideGson().toJson(place)
        editor.putString(KEY_CHOSEN_PLACE, json)
        editor.apply()
    }

    fun getCity(): Place? {
        val json = preferences.getString(KEY_CHOSEN_PLACE, null)
        return provideGson().fromJson(json, Place::class.java)
    }

    private fun addCityToResentSearches(place: Place) {
        val savedJson: MutableList<Place>? = getCitiesFromResentSearches()
        if (savedJson != null) {
            if (savedJson.size > 5) {
                savedJson.removeFirst()
            }
        }
        if (savedJson != null) {
            if (savedJson.contains(place)) {

            } else {
                savedJson.add(place)
                val editor = preferences.edit()
                val json = provideGson().toJson(savedJson)
                editor.putString(KEY_RECENT_SEARCHES, json)
                editor.apply()
            }
        }
    }

    fun getCitiesFromResentSearches(): MutableList<Place>? {
        val json = preferences.getString(KEY_RECENT_SEARCHES, null)
        return if (json != null) {
            val type = object : TypeToken<MutableList<Place?>?>() {}.type
            provideGson().fromJson(json, type)
        } else {
            mutableListOf()
        }
    }
}
    const val KEY_RECENT_SEARCHES = "recentSearches"
    const val KEY_FORECAST_APP = "forecastApp"
    const val KEY_CHOSEN_PLACE = "chosenPlace"


