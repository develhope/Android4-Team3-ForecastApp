package co.develhope.meteoapp.data.dto

import co.develhope.meteoapp.data.domainmodel.Weather
import org.threeten.bp.OffsetDateTime

data class Daily(
    val precipitation_sum: List<Double>,
    val rain_sum: List<Double>,
    val sunrise: List<String>,
    val sunset: List<String>,
    val temperature_2m_max: List<Double>,
    val temperature_2m_min: List<Double>,
    val time: List<OffsetDateTime>,
    val weathercode: List<Int>,
    val windspeed_10m_max: List<Double>
){
    fun toDomain(): List<HomeCardWeather>{
        return time.indices.map{index ->
            HomeCardWeather(
                date = time[index],
                minDegree = temperature_2m_min.get(index).toInt() ?: 0,
                maxDegree = temperature_2m_max.get(index).toInt() ?: 0,
                weather =  (weathercode.get(index).toWeather() ?: 0) as Weather,
                windKmh = windspeed_10m_max.get(index).toInt() ?: 0,
                rainPerc = precipitation_sum.get(index).toInt() ?: 0
            )
        }
    }
}