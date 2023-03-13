package co.develhope.meteoapp.data.domainmodel

import co.develhope.meteoapp.data.domainmodel.Weather


data class DetailedCardForecast(
    val perceivedTemperature: Int,
    val coverage: Int,
    val rain: Int,
    val uvIndex: Int,
    val wind: Int,
    val weather: Weather,
    val celsius: Int,
    val wetness: Int
)
