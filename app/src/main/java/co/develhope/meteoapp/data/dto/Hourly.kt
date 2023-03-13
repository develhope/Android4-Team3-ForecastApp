package co.develhope.meteoapp.data.dto

data class Hourly(
    val rain: List<Double>,
    val showers: List<Double>,
    val snowfall: List<Double>,
    val temperature_2m: List<Double>,
    val relativehumidity_2m: List<Long>,
    val apparent_temperature: List<Double>,
    val diffuse_radiation: List<Double>,
    val cloudcover: List<Long>,
    val time: List<String>,
    val weathercode: List<Int>,
    val windspeed_10m: List<Double>
)
