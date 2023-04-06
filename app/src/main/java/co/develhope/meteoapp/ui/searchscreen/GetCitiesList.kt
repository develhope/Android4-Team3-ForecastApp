package co.develhope.meteoapp.ui.searchscreen

import co.develhope.meteoapp.data.domainmodel.Place
import co.develhope.meteoapp.data.domainmodel.Weather

sealed class GetCitiesList {
    data class Cities(
        val city: Place,
    ) : GetCitiesList()

    data class RecentSearches(
        val recentSearches: String
    ) : GetCitiesList()

}


