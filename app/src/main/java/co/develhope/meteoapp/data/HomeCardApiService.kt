package co.develhope.meteoapp.data

import co.develhope.meteoapp.data.dto.DayForecast
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeCardApiService {
    @GET("v1/forecast")
    suspend fun getHomeCardApiService(
        @Query("latitude") latitude: Double = 41.8955,
        @Query("longitude") longitude: Double = 12.4823,
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
}