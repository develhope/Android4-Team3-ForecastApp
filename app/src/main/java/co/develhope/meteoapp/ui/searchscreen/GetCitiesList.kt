package co.develhope.meteoapp.ui.searchscreen

import co.develhope.meteoapp.data.domainmodel.Place
import co.develhope.meteoapp.data.domainmodel.Weather

sealed class GetCitiesList {
    data class Cities(
        val degrees: Int,
        val weather: Weather,
        val city: Place
    ) : GetCitiesList()

    data class RecentSearches(
        val recentSearches: String
    ) : GetCitiesList()

}

//sealed class GetSearch{
//    data class SetSearch(val tag: String) : GetSearch()
//}

sealed class SearchResults{
    data class Results(val results: List<Place>) : SearchResults()
    data class Errors(val errors: String) : SearchResults()
}