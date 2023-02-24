package co.develhope.meteoapp

import android.os.Build
import org.threeten.bp.OffsetDateTime


object DataSource {

    private val homeItems: List<HomePageItems> = listOf(
        HomeTitle("Palermo", "Sicilia"),

        SpecificDayWeather( date = OffsetDateTime.now(),
                            22,
                            31,
                            12,
                            0,
                            Weather.SUNNY),

        NextDays("PROSSIMI 5 GIORNI"),

        SpecificDayWeather( date = OffsetDateTime.now().plusDays(1),
                            23,
                            29,
                            6,
                            4,
                            Weather.CLOUDY),

        SpecificDayWeather( date = OffsetDateTime.now().plusDays(2),
                            22,
                            31,
                            12,
                            0,
                            Weather.SUNNY),

        SpecificDayWeather( date = OffsetDateTime.now().plusDays(3),
                            20,
                            25,
                            32,
                            90,
                            Weather.RAINY),

        SpecificDayWeather( date = OffsetDateTime.now().plusDays(4),
                            20,
                            29,
                            15,
                            9,
                            Weather.CLOUDY),

        SpecificDayWeather( date = OffsetDateTime.now().plusDays(5),
                            24,
                            33,
                            10,
                            0,
                            Weather.SUNNY),
    )
    fun getHomeItems()= homeItems


}

