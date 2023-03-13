package co.develhope.meteoapp.data.dto

import co.develhope.meteoapp.data.domainmodel.Place

data class SearchCities(
    val generationtime_ms: Double,
    val results: List<Result>
) {
    fun toDomain(): List<Place> {
        return results.map {
            Place(
                name = it.name,
                latitude = it.latitude,
                longitude = it.longitude
            )
        }

    }
}