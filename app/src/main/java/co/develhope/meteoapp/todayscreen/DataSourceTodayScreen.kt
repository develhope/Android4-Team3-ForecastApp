package co.develhope.meteoapp.todayscreen

import co.develhope.meteoapp.Weather
import org.threeten.bp.OffsetDateTime

object DataSourceTodayScreen{
    private val ForecastList: List<Forecast> = listOf(
        TitleForecast(OffsetDateTime.now(), "Napoli", "Campania"),
        HourlyForecastListItem(OffsetDateTime.now(), Weather.SUNNY, 20, 15,
            DetailedCardForecast( 22, 15, 0, 6, 1)),
        HourlyForecastListItem(OffsetDateTime.now().plusHours(1), Weather.CLOUDY, 20, 15,
            DetailedCardForecast( 22, 15, 0, 7, 6)),
        HourlyForecastListItem(OffsetDateTime.now().plusHours(2), Weather.RAINY, 20, 15,
            DetailedCardForecast( 22, 15, 0, 4, 3)),
        HourlyForecastListItem(OffsetDateTime.now().plusHours(3), Weather.NIGHT, 20, 15,
            DetailedCardForecast( 22, 15, 0, 7, 2)),
    )

    fun getHourlyForecast() = ForecastList
}



