package co.develhope.meteoapp

import org.threeten.bp.OffsetDateTime

object DataSource{
    private val ForecastList: List<Forecast> = listOf(
        TitleForecast(OffsetDateTime.now(), "Napoli", "Campania"),
        HourlyForecast(OffsetDateTime.now(), Weather.SUNNY, 20, 15),
        HourlyForecast(OffsetDateTime.now().plusHours(1), Weather.CLOUD, 20, 15),
        HourlyForecast(OffsetDateTime.now().plusHours(2), Weather.RAIN, 20, 15),
        HourlyForecast(OffsetDateTime.now().plusHours(3), Weather.NIGHT, 20, 15),
    )

    fun getHourlyForecast() = ForecastList
}

sealed class Forecast

data class HourlyForecast(
    val date: OffsetDateTime,
    val weather: Weather,
    val celsius: Int,
    val wetness: Int,
): Forecast()

data class DetailedCardForecast(
    val date: OffsetDateTime,
    val weather: Weather,
    val celsius: Int,
    val wetness: Int,  //umidità
    val perceivedTemperature: Int,
    val coverage: Int,
    val rain: Int
): Forecast()

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

