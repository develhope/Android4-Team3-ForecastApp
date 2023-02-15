package co.develhope.meteoapp

import android.os.Build
import org.threeten.bp.OffsetDateTime


object DataSource {

    private val specificDayWeatherList: List<SpecificDayWeather> = listOf(
        SpecificDayWeather(OffsetDateTime.now(),
                            minDegree = 22,
                            maxDegree = 31,
                            windKmh = 12,
                            rainPerc = 0,
                            Weather.SUNNY),
        SpecificDayWeather(OffsetDateTime.now(),
                            minDegree = 22,
                            maxDegree = 31,
                            windKmh = 12,
                            rainPerc = 0,
                            Weather.SUNNY),
        SpecificDayWeather(OffsetDateTime.now(),
                            minDegree = 22,
                            maxDegree = 31,
                            windKmh = 12,
                            rainPerc = 0,
                            Weather.SUNNY),
        SpecificDayWeather(OffsetDateTime.now(),
                            minDegree = 22,
                            maxDegree = 31,
                            windKmh = 12,
                            rainPerc = 0,
                            Weather.SUNNY),
        SpecificDayWeather(OffsetDateTime.now(),
                            minDegree = 22,
                            maxDegree = 31,
                            windKmh = 12,
                            rainPerc = 0,
                            Weather.SUNNY),
        SpecificDayWeather(OffsetDateTime.now(),
                            minDegree = 22,
                            maxDegree = 31,
                            windKmh = 12,
                            rainPerc = 0,
                            Weather.SUNNY),

    )
    fun getspecificDayWeather()= specificDayWeatherList

    private val title = Title("Palermo","Sicily")
    fun getTitle() = title

    private val nextFiveDays = NextDays("Next 5 Days")



}

data class NextDays(
    val nextFiveDays: String
)

data class Title (
    val city: String,
    val region: String
)

data class SpecificDayWeather(
    val date: OffsetDateTime,
    val minDegree: Int,
    val maxDegree: Int,
    val windKmh: Int,
    val rainPerc: Int,
    val weather: Weather
)

enum class Weather{
    SUNNY,
    CLOUDY,
    RAINY
}