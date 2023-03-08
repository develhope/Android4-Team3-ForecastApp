package co.develhope.meteoapp.data

import org.threeten.bp.OffsetDateTime

sealed class HomePageItems() {
    data class NextDays(
        val nextFiveDays: String
    ) : HomePageItems()

    data class HomeTitle(
        val title: Title
    ) : HomePageItems()

    data class SpecificDayWeather(
        val homeCardWeather: HomeCardWeather
    ) : HomePageItems()
}

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







