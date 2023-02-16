package co.develhope.meteoapp

import android.os.Build
import org.threeten.bp.OffsetDateTime


object DataSource {

    private val homeItems: List<HomePageItems> = listOf(
        HomeTitle("Palermo", "Sicily"),

        SpecificDayWeather( cardDayOfWeek = OffsetDateTime.now(),
                            date = OffsetDateTime.now(),
                            22,
                            31,
                            12,
                            0,
                            Weather.SUNNY),

        NextDays("NEXT 5 DAYS"),

        SpecificDayWeather( cardDayOfWeek = OffsetDateTime.now().plusDays(1),
                            date = OffsetDateTime.now().plusDays(1),
                            22,
                            31,
                            12,
                            0,
                            Weather.SUNNY),

        SpecificDayWeather( cardDayOfWeek = OffsetDateTime.now().plusDays(2),
                            date = OffsetDateTime.now().plusDays(2),
                            22,
                            31,
                            12,
                            0,
                            Weather.SUNNY),

        SpecificDayWeather( cardDayOfWeek = OffsetDateTime.now().plusDays(3),
                            date = OffsetDateTime.now().plusDays(3),
                            22,
                            31,
                            12,
                            0,
                            Weather.SUNNY),

        SpecificDayWeather( cardDayOfWeek = OffsetDateTime.now().plusDays(4),
                            date = OffsetDateTime.now().plusDays(4),
                            22,
                            31,
                            12,
                            0,
                            Weather.SUNNY),

        SpecificDayWeather( cardDayOfWeek = OffsetDateTime.now().plusDays(5),
                            date = OffsetDateTime.now().plusDays(5),
                            22,
                            31,
                            12,
                            0,
                            Weather.SUNNY),
    )
    fun getHomeItems()= homeItems


}

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