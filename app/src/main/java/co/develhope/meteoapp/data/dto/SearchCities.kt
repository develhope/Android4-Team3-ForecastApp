package co.develhope.meteoapp.data.dto

import co.develhope.meteoapp.data.GetHourlyForecastList
import co.develhope.meteoapp.data.HourlyForecast
import co.develhope.meteoapp.data.Place
import co.develhope.meteoapp.data.domainmodel.Weather

data class SearchCities(
    val generationtime_ms: Double,
    val results: List<Result>
) {
    fun toDomain(): MutableList<GetHourlyForecastList> {
        val toDomainList = mutableListOf<GetHourlyForecastList>()
        this.results.getOrNull(index = 0).let { result ->
            val degrees = 0
            val weather = Weather.SUNNY
            val city = "${result?.name}, ${result?.country}"
            val hourlyForecast = HourlyForecast(
                degrees,
                weather,
                city = Place(city, latitude = result!!.latitude, longitude = result.longitude)
            )
            toDomainList.add(hourlyForecast)

        }
        return toDomainList

    }
}