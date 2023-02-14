package co.develhope.meteoapp

object SearchSource{
    private val searchList : List<Cities> = listOf()
    private val hourlyForecastList : List<HourlyForecast> = listOf()

    fun getSearchCitiesList() : List<Cities> = searchList
    fun getHourlyForecastList() : List<HourlyForecast> = hourlyForecastList
}

data class HourlyForecast(
    override val degrees : Int,
    override val weather : Weather
) : GetHourlyForecastList(degrees,weather)

data class Cities(
    override val cities: List<Cities>
) : GetSearchCityList(cities)

enum class Weather{
    SUNNY,
    CLOUD,
    RAIN
}

sealed class GetSearchCityList(open val cities: List<Cities>)
sealed class GetHourlyForecastList(open val degrees: Int, open val weather: Weather)



