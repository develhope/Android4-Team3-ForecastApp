package co.develhope.meteoapp.ui.searchscreen

import co.develhope.meteoapp.data.domainmodel.Place

sealed class SearchResults{
    data class Results(val results: List<GetCitiesList>) : SearchResults()
    data class Errors(val errors: String) : SearchResults()
}

sealed class SearchEvent{
    object RetrieveListFromPreferences : SearchEvent()
    data class WritingOnSearchBar(val cityToSearch: String?) : SearchEvent()
    data class AddCityToPreferences(val cityToAdd: Place) : SearchEvent()

}
