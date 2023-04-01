package co.develhope.meteoapp.data

import co.develhope.meteoapp.data.domainmodel.DomainHourlyForecast
import co.develhope.meteoapp.data.domainmodel.Place
import co.develhope.meteoapp.data.dto.DayForecast
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.threeten.bp.OffsetDateTime
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitInstance {
    private fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    private fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {

        return OkHttpClient.Builder()
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    private val retrofitForecast = provideForecastRetrofit(
        client = provideOkHttpClient(loggingInterceptor = provideHttpLoggingInterceptor()),
        gson = provideGson()
    )

    private val retrofitGeocoding = provideGeocodingRetrofit(
        client = provideOkHttpClient(loggingInterceptor = provideHttpLoggingInterceptor()),
        gson = provideGson()
    )


    private val geocodingService: SearchCityService = retrofitGeocoding.create(SearchCityService::class.java)


    private val forecastService : ForecastApiService = retrofitForecast.create(ForecastApiService::class.java)


    private fun provideGson(): Gson = GsonBuilder()
        .registerTypeAdapter(OffsetDateTime::class.java, OffsetDateTimeTypeAdapter())
        .create()

    private fun provideForecastRetrofit(
        client: OkHttpClient,
        gson: Gson
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.open-meteo.com")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
    }

    private fun provideGeocodingRetrofit(
        client: OkHttpClient,
        gson: Gson
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://geocoding-api.open-meteo.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
    }


    suspend fun getPlaces(userSearch: String): List<Place> {
        return this.geocodingService.getDetails(userSearch).toDomain()
    }

    suspend fun retrieveDailyDetails(latitude: Double, longitude: Double): DayForecast {
        return forecastService.getDailyForecast(
            latitude = latitude,
            longitude = longitude,
        )
    }


        suspend fun getHourlyWeather(place: Place, date: OffsetDateTime): List<DomainHourlyForecast> {
            return forecastService.getHourlyWeather(
                latitude = place.latitude,
                longitude = place.longitude,
                start_date = date.toLocalDate().toString(),
                end_date = date.toLocalDate().toString(),
            ).toDomainHourlyForecast()

    }

    /*
    *     getTodayDetailedForecast().plus(
    //TODO replace this when u know how to use a view model
    api.getDtoHourlyWeather(40.8532f, 14.3055f)
    .toDomainHourlyForecast().map {
        Forecast.HourlyForecastListItem(it)
    }
    )*/

}