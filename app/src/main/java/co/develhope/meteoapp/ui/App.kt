package co.develhope.meteoapp.ui

import android.app.Application

val preferences: Preferences by lazy {
    App.preferences!!
}

class App : Application() {
    companion object {
        var preferences: Preferences? = null
        lateinit var instance: App
            private set
    }

    override fun onCreate() {
        super.onCreate()

        instance = this
        preferences = Preferences(applicationContext)
    }
}