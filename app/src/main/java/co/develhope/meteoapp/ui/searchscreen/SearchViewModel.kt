package co.develhope.meteoapp.ui.searchscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.develhope.meteoapp.data.RetrofitInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {
    private var searchData = MutableLiveData<SearchResults>()
    val searchData2: LiveData<SearchResults>
        get() = searchData

    fun searchApi(city : String?) {
        if(city != null && city.isNotEmpty()){
            CoroutineScope(Dispatchers.Main).launch {
                try {
                    searchData.value = RetrofitInstance().getPlaces(city)
                        ?.let { SearchResults.Results(it) }
                } catch (e: Exception) {

                    searchData.value =  SearchResults.Errors(e.localizedMessage!!)

                }
            }
        } else {
            //prendere lista shared pref
            searchData.value = SearchResults.Results(emptyList())
        }

    }
}


