package co.develhope.meteoapp.ui.specificdayscreen.specificdayadapter

import android.annotation.SuppressLint
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import co.develhope.meteoapp.R
import co.develhope.meteoapp.data.domainmodel.Weather
import co.develhope.meteoapp.databinding.HourlyForecastListItemBinding
import co.develhope.meteoapp.ui.specificdayscreen.Forecast

class HourlyListItem(private val listBinding: HourlyForecastListItemBinding) :
    RecyclerView.ViewHolder(listBinding.root) {
    @SuppressLint("SetTextI18n")
    fun bindListItem(item: Forecast.HourlyForecastListItem) {
        listBinding.apply {
            item.domainHourlyForecast.apply {
                hourListItemView.text = "${date.hour}:00"
                detailedCardForecast.apply {
                    iconListItemView.setImageResource(
                        getWeatherImage(weather)
                    )
                    celsiusListItemView.text = "$celsius°"
                    wetnessListItemView.text = "$wetness%"

                    perceivedTemperatureTextView.text = "$perceivedTemperature°"
                    wetnessTextView.text = "$wetness%"
                    coverageTextView.text = "$coverage%"
                    uvIndexTextView.text = "$uvIndex/10"
                    windTextView.text = "${wind}km/h"
                    rainTextView.text = "${rain}cm"

                }
            }

            expandableButtonImageView.setOnClickListener {
                if (hiddenHourlyForecastListItemView.visibility == View.VISIBLE) {
                    TransitionManager.beginDelayedTransition(
                        hourlyForecastListItemView,
                        AutoTransition()
                    )
                    lineImageView.visibility = View.VISIBLE
                    hiddenHourlyForecastListItemView.visibility = View.GONE
                    expandableButtonImageView.setImageResource(R.drawable.baseline_keyboard_arrow_down_24)
                } else {
                    TransitionManager.beginDelayedTransition(
                        hourlyForecastListItemView,
                        AutoTransition()
                    )
                    lineImageView.visibility = View.GONE
                    hiddenHourlyForecastListItemView.visibility = View.VISIBLE
                    expandableButtonImageView.setImageResource(R.drawable.baseline_keyboard_arrow_up_24)
                }
            }

        }
    }

    private fun getWeatherImage(weather: Weather): Int = when (weather) {
        Weather.SUNNY -> R.drawable.sunny_icon
        Weather.MAINLY_CLEAR -> R.drawable.sun_behind_cloud
        Weather.PARTLY_CLOUDY -> R.drawable.sun_large_cloud_icon
        Weather.CLOUDY -> R.drawable.cloud
        Weather.FOG -> R.drawable.fog
        Weather.SUN_RAINY -> R.drawable.sun_behind_rain_cloud_icon
        Weather.RAINY -> R.drawable.cloud_with_rain
        Weather.SNOWFALL -> R.drawable.cloud_with_snow
        Weather.THUNDERSTORM -> R.drawable.cloud_with_lightning
        Weather.RAINY_THUNDERSTORM -> R.drawable.cloud_with_lightning_and_rain
        Weather.NIGHT -> R.drawable.moon_icon
    }
}