package co.develhope.meteoapp

import android.util.Log
import co.develhope.meteoapp.dto.DayForecast
import co.develhope.meteoapp.homescreen.HomeCardApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {

    private val BASE_URL = "https://api.open-meteo.com/"
    private val logging = HttpLoggingInterceptor()
    private val client = OkHttpClient.Builder().addInterceptor(logging).build()

    val retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService = retrofit.create(HomeCardApiService::class.java)


    suspend fun retrieveDailyDetails():DayForecast{
        return try {
            apiService.getHomeCardApiService()
        }catch (e:Exception){
            Log.e("retrieveDailyDetails", "Error: ${e.message}")
            throw  e
        }
    }
}