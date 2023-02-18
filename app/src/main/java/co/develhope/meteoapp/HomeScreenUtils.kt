package co.develhope.meteoapp

import org.threeten.bp.OffsetDateTime

sealed class HomePageItems()

data class NextDays(
    val nextFiveDays: String
):HomePageItems()

data class HomeTitle (
    val city: String,
    val region: String
):HomePageItems()

data class SpecificDayWeather(
    val cardDayOfWeek : OffsetDateTime,
    val date: OffsetDateTime,
    val minDegree: Int,
    val maxDegree: Int,
    val windKmh: Int,
    val rainPerc: Int,
    val weather: Weather
):HomePageItems()

enum class Weather{
    SUNNY,
    CLOUDY,
    RAINY
}