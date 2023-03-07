package co.develhope.meteoapp.dto

import com.google.gson.annotations.SerializedName

data class Daily(
    val precipitationSum: List<Double>,
    val rainSum: List<Double>,
    val sunrise: List<String>,
    val sunset: List<String>,
    val temperature2mMax: List<Double>,
    val temperature2mMin: List<Double>,
    val time: List<String>,
    val weathercode: List<Int>,
    val windspeed_10m_max: List<Double>
)