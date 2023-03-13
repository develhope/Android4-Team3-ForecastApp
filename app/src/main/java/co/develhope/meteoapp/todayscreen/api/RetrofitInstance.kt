package co.develhope.meteoapp.todayscreen.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitInstance {
    private lateinit var retrofit: Retrofit

    private val loggingInterceptor: HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .callTimeout(60, TimeUnit.SECONDS)
        .build()

    fun getClient(): Retrofit{
        retrofit = Retrofit
            .Builder()
            .client(okHttpClient)
            .baseUrl("https://api.open-meteo.com/v1/")
            .addConverterFactory(
                GsonConverterFactory.create()
            ).build()
        return retrofit
    }
}