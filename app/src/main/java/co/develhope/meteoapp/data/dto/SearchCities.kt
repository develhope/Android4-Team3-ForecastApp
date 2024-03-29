package co.develhope.meteoapp.data.dto

import co.develhope.meteoapp.data.domainmodel.Place

data class SearchCities(
    val generationtime_ms: Double?,
    val results: List<Result>?
) {
    fun toDomain(): List<Place>? {
        return results?.map {
            Place(
                name = it.name ?: "-",
                region = it.admin1 ?: "-",
                latitude = it.latitude ?: 0.00,
                longitude = it.longitude ?: 0.00
            )
        }

    }
}