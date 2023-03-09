package co.develhope.meteoapp.searchscreen

import co.develhope.meteoapp.searchscreen.dtos.Result
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchCityService {
    @GET("v1/search")
    suspend fun getDetails(
        @Query("name") name: String
    )
    : Result
}
