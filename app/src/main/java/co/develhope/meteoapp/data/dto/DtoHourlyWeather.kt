package co.develhope.meteoapp.data.dto

import co.develhope.meteoapp.data.domainmodel.DetailedCardForecast
import co.develhope.meteoapp.data.domainmodel.DomainHourlyForecast
import co.develhope.meteoapp.data.domainmodel.Weather
import org.threeten.bp.Instant
import org.threeten.bp.LocalDateTime
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.ZoneId
import org.threeten.bp.format.DateTimeFormatter

data class DtoHourlyWeather(
    val current_weather: CurrentWeather,
    val elevation: Double,
    val generationtime_ms: Double,
    val hourly: Hourly,
    val hourly_units: HourlyUnits,
    val latitude: Double,
    val longitude: Double,
    val timezone: String,
    val timezone_abbreviation: String,
    val utc_offset_seconds: Int
){
    fun toDomainHourlyForecast(): List<DomainHourlyForecast>{
        val list: MutableList<DomainHourlyForecast> = mutableListOf()
        hourly.time.mapIndexed { idx, it ->
            list.add(
                DomainHourlyForecast(
                    date = it,
                    DetailedCardForecast(
                        weather = hourly.weathercode[idx].toWeather(),
                        celsius = hourly.temperature_2m[idx].toInt(),
                        wetness = hourly.relativehumidity_2m[idx].toInt(),

                        perceivedTemperature = hourly.apparent_temperature[idx].toInt(),
                        uvIndex = hourly.uv_index[idx].toInt(),

                        coverage = hourly.cloudcover[idx].toInt(),
                        rain = hourly.rain[idx].toInt(),

                        wind = hourly.windspeed_10m[idx].toInt(),
                    )
                )
            )
        }
        return list
    }
}





