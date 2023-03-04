package co.develhope.meteoapp.data


data class HourlyForecast(
    val degrees : Int,
    val weather : Weather,
    val cities: String
) : GetHourlyForecastList()

data class RecentSearches(
    val recentSearches : String
) : GetHourlyForecastList()

data class SearchBar(
    val searchBar : String
) : GetHourlyForecastList()



sealed class GetHourlyForecastList





