package co.develhope.meteoapp.ui.homescreen

import co.develhope.meteoapp.data.domainmodel.Weather
import org.threeten.bp.OffsetDateTime

data class Title(
    val city: String,
    val region: String,
)

data class HomeCardWeather(
    val date: OffsetDateTime,
    val minDegree: Int,
    val maxDegree: Int,
    val windKmh: Int,
    val rainPerc: Int,
    val weather: Weather
)