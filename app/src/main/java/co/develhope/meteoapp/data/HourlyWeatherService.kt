package co.develhope.meteoapp.data

import co.develhope.meteoapp.data.dto.DtoHourlyWeather
import org.threeten.bp.OffsetDateTime
import retrofit2.http.GET
import retrofit2.http.Query

interface HourlyWeatherService {

    @GET("forecast")
    suspend fun getDtoHourlyWeather(
        @Query("latitude") latitude: Float,
        @Query("longitude") longitude: Float,
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
        @Query("start_date") start_date: String = "${OffsetDateTime.now().toLocalDate()}",
        @Query("end_date") end_date: String = "${OffsetDateTime.now().toLocalDate()}"
    ): DtoHourlyWeather
}