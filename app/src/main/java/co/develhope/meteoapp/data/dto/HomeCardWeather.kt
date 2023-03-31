package co.develhope.meteoapp.data.dto

import co.develhope.meteoapp.data.domainmodel.Weather
import org.threeten.bp.OffsetDateTime

data class HomeCardWeather(
    val date: OffsetDateTime,
    val minDegree: Int,
    val maxDegree: Int,
    val windKmh: Int,
    val rainPerc: Int,
    val weather: Weather
)