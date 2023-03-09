package co.develhope.meteoapp.searchscreen

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    fun interceptor() : Interceptor{
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    val client = OkHttpClient().newBuilder().addInterceptor(interceptor()).build()

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://geocoding-api.open-meteo.com/")  // CONTROLLARE URL
        .addConverterFactory(GsonConverterFactory.create()).client(client).build()
    val apiService: SearchCityService = retrofit.create(SearchCityService::class.java)

}