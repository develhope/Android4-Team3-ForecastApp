package co.develhope.meteoapp.data

import co.develhope.meteoapp.data.domainmodel.Weather

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





