package co.develhope.meteoapp.data

import co.develhope.meteoapp.data.dto.DayForecast
import co.develhope.meteoapp.data.dto.DtoHourlyWeather
import org.threeten.bp.OffsetDateTime
import retrofit2.http.GET
import retrofit2.http.Query

interface ForecastApiService {
    @GET("v1/forecast")
    suspend fun getDailyForecast(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("daily") daily: List<String> = listOf(
            "precipitation_sum",
            "rain_sum",
            "sunrise",
            "sunset",
            "temperature_2m_max",
            "temperature_2m_min",
            "weathercode",
            "windspeed_10m_max"),
        @Query("current_weather") current_weather:Boolean = true,
        @Query("timezone") timezone: String = "Europe/Berlin"
    ): DayForecast

    @GET("v1/forecast")
    suspend fun getHourlyWeather(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("hourly") hourly: List<String> = listOf(
            "temperature_2m",
            "relativehumidity_2m",
            "apparent_temperature",
            "diffuse_radiation",
            "cloudcover",
            "rain",
            "weathercode",
            "windspeed_10m"
        ),
        @Query("current_weather") current_weather: Boolean = true,
        @Query("timezone") timezone: String = "Europe/Berlin",
        @Query("start_date") start_date: String,
        @Query("end_date") end_date: String
    ): DtoHourlyWeather

}