package co.develhope.meteoapp.ui.tomorrowscreen.tomorrowadapter

import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import co.develhope.meteoapp.R
import co.develhope.meteoapp.data.domainmodel.Weather
import co.develhope.meteoapp.databinding.HourlyForecastListItemBinding
import co.develhope.meteoapp.ui.tomorrowscreen.Forecast

class HourlyListItem(private val listBinding: HourlyForecastListItemBinding) :
    RecyclerView.ViewHolder(listBinding.root) {
    fun bindListItem(item: Forecast.HourlyForecastListItem) {
        listBinding.apply {
            item.domainHourlyForecast.apply {
                "${date.hour}:00".also { hourListItemView.text = it }
                detailedCardForecast.apply {
                    iconListItemView.setImageResource(
                        getWeatherImage(weather)
                    )
                    "$celsius°".also { celsiusListItemView.text = it }
                    "$wetness%".also { wetnessListItemView.text = it }

                    "$perceivedTemperature°".also { perceivedTemperatureTextView.text = it }
                    "$wetness%".also { wetnessTextView.text = it }
                    "$coverage%".also { coverageTextView.text = it }
                    "$uvIndex/10".also { uvIndexTextView.text = it }
                    "${wind}km/h".also { windTextView.text = it }
                    "${rain}cm".also { rainTextView.text = it }
                }
            }

            expandableButtonImageView.setOnClickListener {
                setIsRecyclable(false)
                if (hiddenHourlyForecastListItemView.visibility == View.VISIBLE) {
                    TransitionManager.beginDelayedTransition(
                        hourlyForecastListItemView,
                        AutoTransition()
                    )
                    lineImageView.visibility = View.VISIBLE
                    hiddenHourlyForecastListItemView.visibility = View.GONE
                    expandableButtonImageView.setImageResource(R.drawable.upper_arrow)
                    listBinding.expandableButtonImageView.rotation = 0F
                } else {
                    TransitionManager.beginDelayedTransition(
                        hourlyForecastListItemView,
                        AutoTransition()
                    )
                    lineImageView.visibility = View.GONE
                    hiddenHourlyForecastListItemView.visibility = View.VISIBLE
                    expandableButtonImageView.setImageResource(R.drawable.upper_arrow)
                    listBinding.expandableButtonImageView.rotation = 180F
                }
            }
        }
    }
    private fun getWeatherImage(weather: Weather): Int = when (weather) {
        Weather.SUNNY -> R.drawable.sunny_icon
        Weather.CLOUDY -> R.drawable.sun_cloud_icon
        Weather.RAINY -> R.drawable.sun_behind_rain_cloud_icon
        Weather.NIGHT -> R.drawable.moon_icon
    }
}