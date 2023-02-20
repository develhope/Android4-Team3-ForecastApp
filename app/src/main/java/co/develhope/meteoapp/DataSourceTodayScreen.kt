package co.develhope.meteoapp

import org.threeten.bp.OffsetDateTime

object DataSourceTodayScreen{
    private val ForecastList: List<Forecast> = listOf(
        TitleForecast(OffsetDateTime.now(), "Napoli", "Campania"),
        HourlyForecast(OffsetDateTime.now(), Weather.SUNNY, 20, 15,
            DetailedCardForecast( 22, 15, 0, 6, 1)),
        HourlyForecast(OffsetDateTime.now().plusHours(1), Weather.CLOUD, 20, 15,
            DetailedCardForecast( 22, 15, 0, 7, 6)),
        HourlyForecast(OffsetDateTime.now().plusHours(2), Weather.RAIN, 20, 15,
            DetailedCardForecast( 22, 15, 0, 4, 3)),
        HourlyForecast(OffsetDateTime.now().plusHours(3), Weather.NIGHT, 20, 15,
            DetailedCardForecast( 22, 15, 0, 7, 2)),
    )

    fun getHourlyForecast() = ForecastList
}

sealed class Forecast

data class HourlyForecast(
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


enum class Weather{
    SUNNY ,
    CLOUD,
    RAIN,
    NIGHT
}

