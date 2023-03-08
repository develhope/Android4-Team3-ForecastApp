package co.develhope.meteoapp

import android.util.Log
import co.develhope.meteoapp.dto.DayForecast
import co.develhope.meteoapp.homescreen.HomeCardApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {

    private fun interceptor(): Interceptor{
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    private val BASE_URL = "https://api.open-meteo.com/"
    private val client = OkHttpClient.Builder().addInterceptor(interceptor()).build()

    val retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService = retrofit.create(HomeCardApiService::class.java)


    suspend fun retrieveDailyDetails():DayForecast?{
        return try {
            withContext(Dispatchers.Main){
                val response = apiService.getHomeCardApiService()
                response
            }
        }catch (e:Exception){
            Log.d("retrieveDailyDetails", "Error: ${e.message}")
            null
        }
    }
}