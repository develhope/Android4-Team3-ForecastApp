package co.develhope.meteoapp.data

import co.develhope.meteoapp.data.dto.SearchCities
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchCityService {
    @GET("v1/search")
    suspend fun getDetails(
        @Query("name") name: String
    )
    : SearchCities
}
