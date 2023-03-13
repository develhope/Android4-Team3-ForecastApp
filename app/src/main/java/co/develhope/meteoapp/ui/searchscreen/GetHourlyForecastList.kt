package co.develhope.meteoapp.ui.searchscreen

import co.develhope.meteoapp.data.domainmodel.Place
import co.develhope.meteoapp.data.domainmodel.Weather

sealed class GetHourlyForecastList {
    data class HourlyForecast(
        val degrees: Int,
        val weather: Weather,
        val city: Place
    ) : GetHourlyForecastList()

    data class RecentSearches(
        val recentSearches: String
    ) : GetHourlyForecastList()

}