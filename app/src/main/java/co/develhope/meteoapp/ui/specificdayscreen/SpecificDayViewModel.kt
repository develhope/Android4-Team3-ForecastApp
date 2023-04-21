package co.develhope.meteoapp.ui.specificdayscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.develhope.meteoapp.data.DataSource
import co.develhope.meteoapp.data.RetrofitInstance
import co.develhope.meteoapp.data.domainmodel.DomainHourlyForecast
import co.develhope.meteoapp.data.domainmodel.Place
import co.develhope.meteoapp.ui.preferences
import kotlinx.coroutines.launch
import org.threeten.bp.OffsetDateTime

sealed class SpecificDayVMResults{
    data class Result(val result: List<Forecast>): SpecificDayVMResults()
    data class Error(val error: String): SpecificDayVMResults()
}
class SpecificDayViewModel: ViewModel() {
    private var _forecastList: MutableLiveData<SpecificDayVMResults> = MutableLiveData()
    val forecastList: LiveData<SpecificDayVMResults>
        get() = _forecastList

    fun getPrefAndSelectedDayOrNavigate(navigate: () -> Unit){
        val place = preferences.getCity()
        val specificDay = DataSource.getSelectedDay()
        if(place != null && specificDay != null){
            getDetailedForecast(place, specificDay)
        }else{
            navigate()
        }
    }

    private fun getDetailedForecast(place: Place, specificDay: OffsetDateTime) {
        viewModelScope.launch {
            try {
                val detailedForecast : List<DomainHourlyForecast> = RetrofitInstance().getHourlyWeather(place, specificDay)
                _forecastList.value = SpecificDayVMResults.Result(getTodayScreenItems(detailedForecast, place))
            } catch (e: Exception){
                _forecastList.value = e.localizedMessage?.let { SpecificDayVMResults.Error(it) }
            }
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