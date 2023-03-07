package co.develhope.meteoapp.data

import org.threeten.bp.OffsetDateTime

object DataSource{

    private val homeItems: List<HomePageItems> = listOf(
        HomePageItems.HomeTitle(Title("Palermo", "Sicilia", 0.0, 0.0)),

        HomePageItems.SpecificDayWeather(HomeCardWeather(
            date = OffsetDateTime.now(),
            22,
            31,
            12,
            0,
            Weather.SUNNY
        )
        ),

        HomePageItems.NextDays("PROSSIMI 5 GIORNI"),

        HomePageItems.SpecificDayWeather(HomeCardWeather(
            date = OffsetDateTime.now().plusDays(1),
            23,
            29,
            6,
            4,
            Weather.CLOUDY
        )

        ),

        HomePageItems.SpecificDayWeather( HomeCardWeather(
            date = OffsetDateTime.now().plusDays(2),
            22,
            31,
            12,
            0,
            Weather.SUNNY
        )

        ),

        HomePageItems.SpecificDayWeather( HomeCardWeather(
            date = OffsetDateTime.now().plusDays(3),
            20,
            25,
            32,
            90,
            Weather.RAINY
        )
        ),

        HomePageItems.SpecificDayWeather( HomeCardWeather(
            date = OffsetDateTime.now().plusDays(4),
            20,
            29,
            15,
            9,
            Weather.CLOUDY
        )
        ),

        HomePageItems.SpecificDayWeather( HomeCardWeather(
            date = OffsetDateTime.now().plusDays(5),
            24,
            33,
            10,
            0,
            Weather.SUNNY
        )
        ),
    )
    fun getHomeItems()= homeItems


    private val ForecastList: List<Forecast> = listOf(
        TitleForecast(OffsetDateTime.now(), "Napoli", "Campania"),
        HourlyForecastListItem(OffsetDateTime.now(), Weather.SUNNY, 20, 15,
            DetailedCardForecast( 22, 15, 0, 6, 1)
        ),
        HourlyForecastListItem(OffsetDateTime.now().plusHours(1), Weather.CLOUDY, 20, 15,
            DetailedCardForecast( 22, 15, 0, 7, 6)
        ),
        HourlyForecastListItem(OffsetDateTime.now().plusHours(2), Weather.RAINY, 20, 15,
            DetailedCardForecast( 22, 15, 0, 4, 3)
        ),
        HourlyForecastListItem(OffsetDateTime.now().plusHours(3), Weather.NIGHT, 20, 15,
            DetailedCardForecast( 22, 15, 0, 7, 2)
        ),
        HourlyForecastListItem(OffsetDateTime.now().plusHours(4), Weather.NIGHT, 20, 15,
            DetailedCardForecast( 22, 15, 0, 7, 2)
        ),
        HourlyForecastListItem(OffsetDateTime.now().plusHours(5), Weather.NIGHT, 20, 15,
            DetailedCardForecast( 22, 15, 0, 7, 2)
        ),
        HourlyForecastListItem(OffsetDateTime.now().plusHours(6), Weather.NIGHT, 20, 15,
            DetailedCardForecast( 22, 15, 0, 7, 2)
        ),
        HourlyForecastListItem(OffsetDateTime.now().plusHours(7), Weather.NIGHT, 20, 15,
            DetailedCardForecast( 22, 15, 0, 7, 2)
        ),
        HourlyForecastListItem(OffsetDateTime.now().plusHours(8), Weather.NIGHT, 20, 15,
            DetailedCardForecast( 22, 15, 0, 7, 2)
        ),
        HourlyForecastListItem(OffsetDateTime.now().plusHours(9), Weather.NIGHT, 20, 15,
            DetailedCardForecast( 22, 15, 0, 7, 2)
        ),
        HourlyForecastListItem(OffsetDateTime.now().plusHours(10), Weather.NIGHT, 20, 15,
            DetailedCardForecast( 22, 15, 0, 7, 2)
        ),
        HourlyForecastListItem(OffsetDateTime.now().plusHours(10), Weather.NIGHT, 20, 15,
            DetailedCardForecast( 22, 15, 0, 7, 2)
        ),


    )

    fun getTodayDetailedForecast() = ForecastList

    private val searchList : List<GetHourlyForecastList> = listOf(
        SearchBar(""),
        RecentSearches("Ricerche Recenti"),
        HourlyForecast(12, Weather.SUNNY, "Palermo"),
        HourlyForecast(12, Weather.CLOUDY, "Catanzaro"),
        HourlyForecast(12, Weather.RAINY, "Roma")
    )
    fun getSearchCitiesList() : List<GetHourlyForecastList>{
        return searchList
    }
}



