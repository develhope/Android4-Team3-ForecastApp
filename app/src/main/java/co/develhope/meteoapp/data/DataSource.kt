package co.develhope.meteoapp.data

import co.develhope.meteoapp.data.domainmodel.Weather
import co.develhope.meteoapp.data.domainmodel.DetailedCardForecast
import co.develhope.meteoapp.data.domainmodel.DomainHourlyForecast
import co.develhope.meteoapp.data.domainmodel.Place
import co.develhope.meteoapp.data.dto.HomeCardWeather
import co.develhope.meteoapp.ui.searchscreen.GetHourlyForecastList
import co.develhope.meteoapp.ui.todayscreen.Forecast
import org.threeten.bp.OffsetDateTime

object DataSource{

    private var selectedCity : Place? = null
    fun setSelectedCity(place: Place){
        selectedCity = place
    }
    fun getSelectedCity(): Place?{
        return  selectedCity
    }

    private val searchList : List<GetHourlyForecastList> = listOf(
        GetHourlyForecastList.RecentSearches("Ricerche Recenti"),
        GetHourlyForecastList.HourlyForecast(12, Weather.SUNNY, Place("Palermo", "Sicilia", 38.13205, 13.33561)),
        GetHourlyForecastList.HourlyForecast(12, Weather.CLOUDY, Place("Catanzaro", "Calabria",38.8824700, 16.60008600)),
        GetHourlyForecastList.HourlyForecast(12, Weather.RAINY, Place("Roma", "Lazio",41.8919300, 12.5113300))
    )
    fun getSearchCitiesList() : List<GetHourlyForecastList>{
        return searchList
    }


}



