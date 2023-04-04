package co.develhope.meteoapp.ui.tomorrowscreen

import co.develhope.meteoapp.data.domainmodel.DomainHourlyForecast
import co.develhope.meteoapp.data.domainmodel.Place

sealed class Forecast {
    data class HourlyForecastListItem(
        val domainHourlyForecast: DomainHourlyForecast
    ) : Forecast()
    
    data class TitleForecast(
        val domainHourlyForecast: DomainHourlyForecast,
        val place: Place
    ) : Forecast()
}