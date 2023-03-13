package co.develhope.meteoapp.searchscreen

import co.develhope.meteoapp.searchscreen.dtos.Result
import co.develhope.meteoapp.searchscreen.dtos.SearchCities
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchCityService {
    @GET("v1/search")
    suspend fun getDetails(
        @Query("name") name: String
    )
    : SearchCities
}
