package co.develhope.meteoapp.ui.todayscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.develhope.meteoapp.data.RetrofitInstance
import co.develhope.meteoapp.data.domainmodel.DomainHourlyForecast
import co.develhope.meteoapp.data.domainmodel.Place
import kotlinx.coroutines.launch
import org.threeten.bp.OffsetDateTime

class TodayScreenViewModel: ViewModel() {
    private val _forecastList: MutableLiveData<List<Forecast>> = MutableLiveData()
    val forecastList: LiveData<List<Forecast>>
        get() = _forecastList

    private val _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String>
        get() = _error

    fun getDetailedForecast(place: Place, specificDay: OffsetDateTime) {
        viewModelScope.launch {
            try {
                val detailedForecast : List<DomainHourlyForecast> = RetrofitInstance().getHourlyWeather(place, specificDay)
                _forecastList.value = getTodayScreenItems(detailedForecast, place)
            } catch (e: Exception){ _error.value = e.localizedMessage }
        }
    }

    private fun getTodayScreenItems(detailedForecast: List<DomainHourlyForecast>, place: Place): List<Forecast> {
        val list: MutableList<Forecast> = mutableListOf()   //list to return
        list.add(Forecast.TitleForecast(detailedForecast.first(), place))   //adding the title to the list
        detailedForecast.map {  //adding all the hourly view for the entire day (0 to 23)
            list.add(Forecast.HourlyForecastListItem(it))
        }
        return list
    }
}