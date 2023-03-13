package co.develhope.meteoapp.data

import co.develhope.meteoapp.data.domainmodel.Weather
import co.develhope.meteoapp.data.domainmodel.DetailedCardForecast
import co.develhope.meteoapp.data.domainmodel.DomainHourlyForecast
import co.develhope.meteoapp.data.domainmodel.Place
import co.develhope.meteoapp.ui.homescreen.HomeCardWeather
import co.develhope.meteoapp.ui.homescreen.HomePageItems
import co.develhope.meteoapp.ui.searchscreen.GetHourlyForecastList
import co.develhope.meteoapp.ui.todayscreen.Forecast
import org.threeten.bp.OffsetDateTime

object DataSource{

    private var selectedCity : Place? = Place(
        name = "Pippo",
        latitude = 40.8531,
        longitude = 14.3055
    )
    fun setSelectedCity(place: Place){
        selectedCity = place
    }
    fun getSelectedCity(): Place?{
        return  selectedCity
    }

    val cardView = HomeCardWeather(
        date = OffsetDateTime.now(),
        22,
        31,
        12,
        0,
        Weather.SUNNY)



    private val forecast: MutableList<Forecast> = mutableListOf(
        Forecast.TitleForecast(
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
        GetHourlyForecastList.RecentSearches("Ricerche Recenti"),
        GetHourlyForecastList.HourlyForecast(12, Weather.SUNNY, Place("Palermo", 38.13205, 13.33561)),
        GetHourlyForecastList.HourlyForecast(12, Weather.CLOUDY, Place("Catanzaro", 38.8824700, 16.60008600)),
        GetHourlyForecastList.HourlyForecast(12, Weather.RAINY, Place("Roma", 41.8919300, 12.5113300))
    )
    fun getSearchCitiesList() : List<GetHourlyForecastList>{
        return searchList
    }


}



