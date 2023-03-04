package co.develhope.meteoapp.homescreen

import co.develhope.meteoapp.*
import org.threeten.bp.OffsetDateTime


object DataSource {

    private val homeItems: List<HomePageItems> = listOf(
        HomePageItems.HomeTitle("Palermo", "Sicilia"),

        HomePageItems.SpecificDayWeather(
            date = OffsetDateTime.now(),
            22,
            31,
            12,
            0,
            Weather.SUNNY
        ),

        HomePageItems.NextDays("PROSSIMI 5 GIORNI"),

        HomePageItems.SpecificDayWeather(
            date = OffsetDateTime.now().plusDays(1),
            23,
            29,
            6,
            4,
            Weather.CLOUDY
        ),

        HomePageItems.SpecificDayWeather(
            date = OffsetDateTime.now().plusDays(2),
            22,
            31,
            12,
            0,
            Weather.SUNNY
        ),

        HomePageItems.SpecificDayWeather(
            date = OffsetDateTime.now().plusDays(3),
            20,
            25,
            32,
            90,
            Weather.RAINY
        ),

        HomePageItems.SpecificDayWeather(
            date = OffsetDateTime.now().plusDays(4),
            20,
            29,
            15,
            9,
            Weather.CLOUDY
        ),

        HomePageItems.SpecificDayWeather(
            date = OffsetDateTime.now().plusDays(5),
            24,
            33,
            10,
            0,
            Weather.SUNNY
        ),
    )
    fun getHomeItems()= homeItems


}

