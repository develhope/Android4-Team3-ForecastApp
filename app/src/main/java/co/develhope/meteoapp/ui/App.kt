package co.develhope.meteoapp.ui

import android.app.Application
import co.develhope.meteoapp.data.Preferences
import com.google.gson.Gson

val preferences: Preferences by lazy {
    App.preferences!!
}

class App : Application() {
    companion object {
        var preferences: Preferences? = null
        var gson : Gson? = null
        lateinit var instance: App
            private set
    }

    override fun onCreate() {
        super.onCreate()

        instance = this
        preferences = Preferences(applicationContext)
        gson = Gson()
    }
}