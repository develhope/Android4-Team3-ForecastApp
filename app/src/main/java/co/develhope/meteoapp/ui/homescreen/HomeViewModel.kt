package co.develhope.meteoapp.ui.homescreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.develhope.meteoapp.data.RetrofitInstance
import co.develhope.meteoapp.data.domainmodel.Place
import co.develhope.meteoapp.data.dto.HomeCardWeather
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel() : ViewModel() {

    sealed class NetworkCall {
        data class Success(val dailyInfo: List<HomeCardWeather>) : NetworkCall()
        data class Error(val error: String) : NetworkCall()
    }


    private var _dailyForecast = MutableLiveData<NetworkCall>()
    val dailyForecast: LiveData<NetworkCall>
        get() = _dailyForecast


    fun retrieveForecast(place: Place) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                _dailyForecast.value = NetworkCall.Success(
                    RetrofitInstance().retrieveDailyDetails(
                        place.latitude,
                        place.longitude
                    ).daily.toDomain()
                )
            } catch (e: Exception) {
                _dailyForecast.value = NetworkCall.Error("Error: ${e.localizedMessage}")
            }
        }
    }
}