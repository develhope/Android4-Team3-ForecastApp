package co.develhope.meteoapp.ui.todayscreen.todayadapter

import android.annotation.SuppressLint
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import co.develhope.meteoapp.R
import co.develhope.meteoapp.data.domainmodel.Weather
import co.develhope.meteoapp.databinding.HourlyForecastListItemBinding
import co.develhope.meteoapp.ui.todayscreen.Forecast

class HourlyViewHolder(
    private val listBinding: HourlyForecastListItemBinding,
    private val setOpenedCard: (Forecast.HourlyForecastListItem?) -> Unit
) : RecyclerView.ViewHolder(listBinding.root) {
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
                    setOpenedCard(null)
                    closeCard()
                } else {
                    setOpenedCard(item)
                    openCard()
                }
            }
        }
    }

    fun closeCard(){
        listBinding.apply {
            TransitionManager.beginDelayedTransition(
                hourlyForecastListItemView,
                AutoTransition()
            )
            lineImageView.visibility = View.VISIBLE
            hiddenHourlyForecastListItemView.visibility = View.GONE
            expandableButtonImageView.setImageResource(R.drawable.upper_arrow)
            listBinding.expandableButtonImageView.rotation = 0F

        }
    }
    fun openCard(){
        listBinding.apply {
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
    private fun getWeatherImage(weather: Weather): Int = when (weather) {
        Weather.SUNNY -> R.drawable.sunny_icon
        Weather.CLOUDY -> R.drawable.sun_cloud_icon
        Weather.RAINY -> R.drawable.sun_behind_rain_cloud_icon
        Weather.NIGHT -> R.drawable.moon_icon
    }
}