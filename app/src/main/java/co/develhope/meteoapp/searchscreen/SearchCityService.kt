package co.develhope.meteoapp.searchscreen

import android.view.View
import co.develhope.meteoapp.NavGraphDirections
import retrofit2.http.GET

interface SearchCityService {
    @GET("v1/search?name=palermo")  // CONTROLLARE ENDPOINT
    suspend fun getDetails() : SearchCities
}

interface Filterable : View.OnFocusChangeListener {
    override fun onFocusChange(p0: View?, p1: Boolean) {
    }
}

interface Navigable{
    fun navigate(card : NavGraphDirections.SearchScreenToHomeScreen)
}