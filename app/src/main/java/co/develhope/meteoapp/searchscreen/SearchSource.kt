package co.develhope.meteoapp.searchscreen

import co.develhope.meteoapp.Weather

object SearchSource{
    private val searchList : List<GetHourlyForecastList> = listOf(
        RecentSearches("Ricerche Recenti"),
        HourlyForecast(12, Weather.SUNNY, Place("Palermo", 0.000, 0.000)),
        HourlyForecast(12, Weather.CLOUDY, Place("Catanzaro", 0.000, 0.000)),
        HourlyForecast(12, Weather.RAINY, Place("Roma", 0.000, 0.000))
    )
    fun getSearchCitiesList() : List<GetHourlyForecastList>{
        return searchList
    }

}

data class HourlyForecast(
    val degrees : Int,
    val weather : Weather,
    val city: Place
) : GetHourlyForecastList()

data class RecentSearches(
    val recentSearches : String
) : GetHourlyForecastList()

data class SearchBar(
    val searchBar : String
)

data class Place(
    val name: String,
    val latitude: Double,
    val longitude: Double  // SPOSTARE CLASSE
)


sealed class GetHourlyForecastList





