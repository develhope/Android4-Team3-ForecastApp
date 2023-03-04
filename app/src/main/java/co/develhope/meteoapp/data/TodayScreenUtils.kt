package co.develhope.meteoapp.data

import org.threeten.bp.OffsetDateTime


sealed class Forecast

data class HourlyForecastListItem(
    val date: OffsetDateTime,
    val weather: Weather,
    val celsius: Int,
    val wetness: Int,
    val cardValues: DetailedCardForecast,
): Forecast()

data class DetailedCardForecast(
    val perceivedTemperature: Int,
    val coverage: Int,
    val rain: Int,
    val uvIndex: Int,
    val wind: Int
)

data class TitleForecast(
    val date: OffsetDateTime,
    val city: String,
    val region: String
): Forecast()