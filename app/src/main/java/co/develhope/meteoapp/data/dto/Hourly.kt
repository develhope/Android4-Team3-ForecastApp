package co.develhope.meteoapp.data.dto

import org.threeten.bp.OffsetDateTime

data class Hourly(
    val rain: List<Double>,
    val showers: List<Double>,
    val snowfall: List<Double>,
    val temperature_2m: List<Double>,
    val relativehumidity_2m: List<Long>,
    val apparent_temperature: List<Double>,
    val uv_index: List<Double>,
    val cloudcover: List<Long>,
    val time: List<OffsetDateTime>,
    val weathercode: List<Int>,
    val windspeed_10m: List<Double>
)
