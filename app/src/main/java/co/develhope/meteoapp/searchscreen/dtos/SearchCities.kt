package co.develhope.meteoapp.searchscreen.dtos

import co.develhope.meteoapp.searchscreen.dtos.Result

data class SearchCities(
    val generationtime_ms: Double,
    val results: List<Result>
)