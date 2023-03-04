package co.develhope.meteoapp.searchscreen

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://geocoding-api.open-meteo.com/v1/search?name=palermo")  // CONTROLLARE URL
        .addConverterFactory(GsonConverterFactory.create()).build()
    val apiService: SearchCityService = retrofit.create(SearchCityService::class.java)

}