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

    fun searchApi() {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                searchData.value = SearchResults.Results(RetrofitInstance().getPlaces("Palermo"))
            } catch (e: Exception) {

                //Log.d("Search","ERROR : ${e.message},${e.cause}")

                searchData.value =  SearchResults.Errors(e.localizedMessage!!)
                //searchData.value = e.localizedMessage?.let { SearchResults.Errors(it) }
            }
        }
    }
}
