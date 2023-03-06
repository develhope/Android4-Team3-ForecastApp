package co.develhope.meteoapp

import co.develhope.meteoapp.homescreen.HomeCardApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class RetrofitInstance {

    val BASE_URL = "https://api.open-meteo.com/"
    val logging = HttpLoggingInterceptor()
    val client = OkHttpClient.Builder().addInterceptor(logging).build()

    val retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService = retrofit.create(HomeCardApiService::class.java)
}