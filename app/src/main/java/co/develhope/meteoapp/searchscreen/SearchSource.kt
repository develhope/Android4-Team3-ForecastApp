package co.develhope.meteoapp.searchscreen

import co.develhope.meteoapp.Weather

object SearchSource{
    private val searchList : List<GetHourlyForecastList> = listOf(
        RecentSearches("Ricerche Recenti"),
        HourlyForecast(12, Weather.SUNNY, Place("Palermo", 38.13205, 13.33561)),
        HourlyForecast(12, Weather.CLOUDY, Place("Catanzaro", 38.8824700, 16.60008600)),
        HourlyForecast(12, Weather.RAINY, Place("Roma", 41.8919300, 12.5113300))
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

//data class SearchBar(
//    val searchBar : SearchView
//)

data class Place(
    val name: String,
    val latitude: Double,
    val longitude: Double  // SPOSTARE CLASSE
)


sealed class GetHourlyForecastList








