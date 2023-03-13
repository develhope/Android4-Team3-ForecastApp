package co.develhope.meteoapp.data

import co.develhope.meteoapp.todayscreen.domain.DetailedCardForecast
import co.develhope.meteoapp.todayscreen.domain.DomainHourlyForecast
import org.threeten.bp.OffsetDateTime

object DataSource{

    private var selectedCity : Place? = null
    fun setSelectedCity(place: Place){
        selectedCity = place
    }
    fun getSelectedCity(): Place?{
        return selectedCity
    }

    private val homeItems: List<HomePageItems> = listOf(
        HomePageItems.HomeTitle(Title("Palermo", "Sicilia")),

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

    val cardView = HomeCardWeather(
        date = OffsetDateTime.now(),
        22,
        31,
        12,
        0,
        Weather.SUNNY)
    fun getHomeItems()= homeItems


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

    private val searchList : List<GetHourlyForecastList> = listOf(
        RecentSearches("Ricerche Recenti"),
        HourlyForecast(12, Weather.SUNNY, Place("Palermo", 38.13205, 13.33561)),
        HourlyForecast(12, Weather.CLOUDY, Place("Catanzaro", 38.8824700, 16.60008600)),
        HourlyForecast(12, Weather.RAINY, Place("Roma", 41.8919300, 12.5113300))
    )
    fun getSearchCitiesList() : List<GetHourlyForecastList>{
        return searchList
    }

    fun Int.toWeather() : Weather{
        return when(this){
            0 -> Weather.SUNNY
            1,2,3 -> Weather.CLOUDY
            45,48 -> Weather.CLOUDY
            51,53,55 -> Weather.RAINY
            56,57 ->Weather.RAINY
            71,73,75 ->Weather.RAINY
            80,81,82 -> Weather.RAINY
            95 -> Weather.RAINY
            96,99 ->Weather.RAINY
            else -> Weather.CLOUDY
        }
    }
}



