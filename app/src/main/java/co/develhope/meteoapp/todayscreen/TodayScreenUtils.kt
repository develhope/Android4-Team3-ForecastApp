package co.develhope.meteoapp.todayscreen

import co.develhope.meteoapp.todayscreen.domain.DomainHourlyForecast
import co.develhope.meteoapp.todayscreen.domain.Place

sealed class Forecast

data class HourlyForecastListItem(
    val domainHourlyForecast: DomainHourlyForecast
): Forecast()

data class TitleForecast(
    //TODO Remove all value and get them from data class "Place"
    // from eufemia's code
    val domainHourlyForecast: DomainHourlyForecast,
    val place: Place
): Forecast()