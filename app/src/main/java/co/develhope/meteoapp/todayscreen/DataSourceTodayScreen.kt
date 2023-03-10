package co.develhope.meteoapp.todayscreen

import co.develhope.meteoapp.Weather
import co.develhope.meteoapp.todayscreen.domain.DetailedCardForecast
import co.develhope.meteoapp.todayscreen.domain.DomainHourlyForecast
import co.develhope.meteoapp.todayscreen.domain.Place
import org.threeten.bp.OffsetDateTime


//+++++++++++++++++++++++++++   DONE   +++++++++++++++++++++++++++//
object DataSourceTodayScreen{
    private val forecast: MutableList<Forecast> = mutableListOf(
        TitleForecast(
            DomainHourlyForecast(
                OffsetDateTime.now(),
                DetailedCardForecast(
                    perceivedTemperature = 1,
                    coverage = 1,
                    rain = 1,
                    uvIndex = 1,
                    wind = 1,
                    wetness = 1,
                    weather = Weather.SUNNY,
                    celsius = 1
                )
            ),
            Place(
                name = "Naples",
                latitude = 23.2323,
                longitude = 23.2323
            )
        )
    )

    fun getTodayDetailedForecast(): List<Forecast>{
        return forecast
    }
}



