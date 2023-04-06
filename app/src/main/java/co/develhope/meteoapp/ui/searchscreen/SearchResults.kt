package co.develhope.meteoapp.ui.searchscreen

import co.develhope.meteoapp.data.domainmodel.Place

sealed class SearchResults{
    data class Results(val results: List<Place>) : SearchResults()
    data class Errors(val errors: String) : SearchResults()
}
