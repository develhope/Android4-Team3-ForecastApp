package co.develhope.meteoapp.data

import org.threeten.bp.OffsetDateTime

import co.develhope.meteoapp.todayscreen.domain.DomainHourlyForecast
import co.develhope.meteoapp.todayscreen.domain.Place

sealed class Forecast

data class HourlyForecastListItem(
    val domainHourlyForecast: DomainHourlyForecast
): Forecast()

data class DetailedCardForecast(
    val perceivedTemperature: Int,
    val coverage: Int,
    val rain: Int,
    val uvIndex: Int,
    val wind: Int
)

data class TitleForecast(
    //TODO Remove all value and get them from data class "Place"
    // from eufemia's code
    val domainHourlyForecast: DomainHourlyForecast,
    val place: Place
): Forecast()