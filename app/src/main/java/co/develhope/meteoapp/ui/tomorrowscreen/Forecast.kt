package co.develhope.meteoapp.ui.tomorrowscreen

import co.develhope.meteoapp.data.domainmodel.DomainHourlyForecast
import co.develhope.meteoapp.data.domainmodel.Place

sealed class Forecast() {
    //This will make the id available on Forecast, but delegates the storage to whatever implements it.
    abstract val id: Int
    data class HourlyForecastListItem(
        val domainHourlyForecast: DomainHourlyForecast,
        override val id: Int = domainHourlyForecast.date.hour
    ) : Forecast()
    
    data class TitleForecast(
        val domainHourlyForecast: DomainHourlyForecast,
        val place: Place,
        override val id: Int = domainHourlyForecast.date.year
    ) : Forecast()
}