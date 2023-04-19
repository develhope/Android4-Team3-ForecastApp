package co.develhope.meteoapp.ui.searchscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.develhope.meteoapp.data.RetrofitInstance
import co.develhope.meteoapp.data.domainmodel.Place
import co.develhope.meteoapp.ui.preferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {
    private var searchData = MutableLiveData<SearchResults>()
    val searchData2: LiveData<SearchResults>
        get() = searchData

    fun send(event: SearchEvent){
        when(event){
            SearchEvent.RetrieveListFromPreferences -> returnPreferences()
            is SearchEvent.WritingOnSearchBar -> searchCity(event.cityToSearch)
            is SearchEvent.AddCityToPreferences -> addCityToPreferences(event.cityToAdd)
        }
    }

    private fun addCityToPreferences(place: Place) {
        preferences.setCity(place)
    }

    private fun returnPreferences() {
        preferences.getCitiesFromResentSearches()?.let { listToUi(it.asReversed()) }
    }

    private fun searchCity(city : String?) {
        if(city != null && city.length > 1){
            apiCall(city)
        } else if(city!!.isEmpty()){
            returnPreferences()
        } else {
            //TODO se cancelli troppo velocemnte non ti ritorna questa lista
            returnPreferences()
        }
    }

    private fun apiCall(city: String?) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                RetrofitInstance().getPlaces(city)?.let { listToUi(it) }
            } catch (e: Exception) {
                searchData.value =  SearchResults.Errors(e.localizedMessage!!)
            }
        }
    }

    private fun listToUi(list: List<Place>){
        val getCitiesList = mutableListOf<GetCitiesList>(GetCitiesList.RecentSearches)
        getCitiesList.addAll(1, list.map { GetCitiesList.Cities(it) })

        searchData.value = SearchResults.Results(getCitiesList)
    }
}


