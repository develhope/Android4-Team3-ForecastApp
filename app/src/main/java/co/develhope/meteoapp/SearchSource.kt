package co.develhope.meteoapp

object SearchSource{
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





