package co.develhope.meteoapp.data

import co.develhope.meteoapp.data.domainmodel.Place
import co.develhope.meteoapp.ui.searchscreen.GetCitiesList
import org.threeten.bp.OffsetDateTime

object DataSource{

    private var selectedCity : Place? = null
    fun setSelectedCity(place: Place){
        selectedCity = place
    }
    fun getSelectedCity(): Place?{
        return  selectedCity
    }

    private var selectedDay : OffsetDateTime? = null

    fun setSelectedDay(offsetDateTime: OffsetDateTime){
        selectedDay = offsetDateTime
    }

    fun getSelectedDay(): OffsetDateTime? = selectedDay

    private val searchList : List<GetCitiesList> = mutableListOf(
        GetCitiesList.Cities(Place("Palermo", "Sicilia", 38.13205, 13.33561)),
        GetCitiesList.Cities(Place("Catanzaro", "Calabria",38.8824700, 16.60008600)),
        GetCitiesList.Cities(Place("Roma", "Lazio",41.8919300, 12.5113300))
    )

    private val recentSearches : GetCitiesList = GetCitiesList.RecentSearches("Ricerche Recenti")

    fun getSearchCitiesList() : List<GetCitiesList>{
        return searchList
    }

    fun getRecentSearches() : GetCitiesList{
        return recentSearches
    }



}



