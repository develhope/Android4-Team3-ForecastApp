package co.develhope.meteoapp

object SearchSource{
    private val searchList : List<GetHourlyForecastList> = listOf(
        SearchBar(""),
        RecentSearches("Ricerche Recenti"),
        HourlyForecast("12°", Weather.SUNNY, "Palermo"),
        HourlyForecast("16°", Weather.CLOUD, "Catanzaro"),
        HourlyForecast("16°", Weather.RAIN, "Roma")
    )
    fun getSearchCitiesList() : List<GetHourlyForecastList>{
        return searchList
    }

}

data class HourlyForecast(
    val degrees : String,
    val weather : Weather,
    val cities: String
) : GetHourlyForecastList()

data class RecentSearches(
    val recentSearches : String
) : GetHourlyForecastList()

data class SearchBar(
    val searchBar : String
) : GetHourlyForecastList()

enum class Weather{
    SUNNY,
    CLOUD,
    RAIN
}

sealed class GetHourlyForecastList





