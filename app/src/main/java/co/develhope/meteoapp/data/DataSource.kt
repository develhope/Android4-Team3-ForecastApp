package co.develhope.meteoapp.data

import co.develhope.meteoapp.data.domainmodel.Weather
import co.develhope.meteoapp.data.domainmodel.Place
import co.develhope.meteoapp.ui.searchscreen.GetCitiesList

object DataSource{

    private var selectedCity : Place? = Place(
        name = "Napoli",
        region = "Campania",
        latitude = 40.8531,
        longitude = 14.3055
    )
    fun setSelectedCity(place: Place){
        selectedCity = place
    }
    fun getSelectedCity(): Place?{
        return  selectedCity
    }

    private val searchList : List<GetCitiesList> = listOf(
        GetCitiesList.Cities(12, Weather.SUNNY, Place("Palermo", "Sicilia", 38.13205, 13.33561)),
        GetCitiesList.Cities(12, Weather.CLOUDY, Place("Catanzaro", "Calabria",38.8824700, 16.60008600)),
        GetCitiesList.Cities(12, Weather.RAINY, Place("Roma", "Lazio",41.8919300, 12.5113300))
    )

    private val recentSearches : GetCitiesList = GetCitiesList.RecentSearches("Ricerche Recenti")

    fun getSearchCitiesList() : List<GetCitiesList>{
        return searchList
    }

    fun getRecentSearches() : GetCitiesList{
        return recentSearches
    }


}



