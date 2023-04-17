package co.develhope.meteoapp.ui.searchscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.develhope.meteoapp.data.Preferences
import co.develhope.meteoapp.data.RetrofitInstance
import co.develhope.meteoapp.ui.preferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {
    private var searchData = MutableLiveData<SearchResults>()
    val searchData2: LiveData<SearchResults>
        get() = searchData

    fun searchApi(city : String?) {
        if(city != null && city.length > 1){
            CoroutineScope(Dispatchers.Main).launch {
                try {
                    searchData.value = RetrofitInstance().getPlaces(city)
                        ?.let { SearchResults.Results(it) }
                } catch (e: Exception) {
                    searchData.value =  SearchResults.Errors(e.localizedMessage!!)
                }
            }
        } else {
            //TODO se cancelli troppo velocemnte non ti ritorna questa lista
            searchData.value = SearchResults.Results(preferences.getCitiesFromResentSearches().asReversed())
        }

    }
}


