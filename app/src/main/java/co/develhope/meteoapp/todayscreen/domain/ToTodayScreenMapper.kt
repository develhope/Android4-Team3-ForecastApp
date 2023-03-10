package co.develhope.meteoapp.todayscreen.domain

import co.develhope.meteoapp.Weather
import co.develhope.meteoapp.todayscreen.dto.DtoHourlyWeather
import org.threeten.bp.*
import org.threeten.bp.format.DateTimeFormatter

object ToTodayScreenMapper {
    /**
    * Function used to convert a number from network call to an element of the weather enum,
     * the number represent a scale of the WMO Weather interpretation code
    * */
    private fun getWeather(weatherCode: Int): Weather {
        return when(weatherCode){
            in 0..3 -> Weather.SUNNY
            in 45..57 -> Weather.CLOUDY
            in 61..99 -> Weather.RAINY
            else -> Weather.NIGHT
        }
    }
    fun DtoHourlyWeather.toDomainHourlyForecast(): List<DomainHourlyForecast>{
        val list: MutableList<DomainHourlyForecast> = mutableListOf()
        hourly.time.mapIndexed { idx, it ->
            list.add(
                DomainHourlyForecast(
                    date = OffsetDateTime.of(LocalDateTime.parse(it, DateTimeFormatter.ofPattern("[yyyy-MM-dd'T'HH:mm]")), ZoneId.of("Europe/Berlin").rules.getOffset(
                        Instant.now())),// LocalDate.parse(it, dateTimeFormatter),
                    DetailedCardForecast(
                        weather = getWeather(hourly.weathercode[idx]),
                        celsius = hourly.temperature_2m[idx].toInt(),
                        wetness = hourly.relativehumidity_2m[idx].toInt(),

                        perceivedTemperature = hourly.apparent_temperature[idx].toInt(),
                        uvIndex = hourly.diffuse_radiation[idx].toInt(),

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