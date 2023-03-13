package co.develhope.meteoapp.ui.todayscreen

import co.develhope.meteoapp.data.Place
import co.develhope.meteoapp.data.domainmodel.DomainHourlyForecast

sealed class Forecast {
    data class HourlyForecastListItem(
        val domainHourlyForecast: DomainHourlyForecast
    ) : Forecast()
    
    data class TitleForecast(
        //TODO Remove all value and get them from data class "Place"
        // from eufemia's code
        val domainHourlyForecast: DomainHourlyForecast,
        val place: Place
    ) : Forecast()
}