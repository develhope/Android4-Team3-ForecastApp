package co.develhope.meteoapp.ui.specificdayscreen

import android.annotation.SuppressLint
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.develhope.meteoapp.R
import co.develhope.meteoapp.data.domainmodel.Weather
import co.develhope.meteoapp.databinding.HourlyForecastListItemBinding
import co.develhope.meteoapp.databinding.HourlyForecastTitleItemBinding
import org.threeten.bp.format.TextStyle
import java.util.*

class SpecificDayAdapter(private val list: List<Forecast>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private enum class ViewType(val value: Int) {
        TITLE_FORECAST(1),
        HOURLY_FORECAST_LIST_ITEM(2)
    }

    inner class HourlyTitleItem(private val titleBinding: HourlyForecastTitleItemBinding) :
        RecyclerView.ViewHolder(titleBinding.root) {
        @SuppressLint("SetTextI18n")
        fun bindTitleItem(item: Forecast.TitleForecast) {
            titleBinding.apply {
                cityAndRegionTextView.text = "${item.place.name}, " + item.place.region
                item.domainHourlyForecast.apply {
                    currentDayTextView.text =
                        date.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault())
                    dateTextView.text = (
                            date.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault())
                                    + " " + date.dayOfMonth.toString() + " " +
                                    date.month.getDisplayName(TextStyle.FULL, Locale.getDefault())
                            ).lowercase()
                }
            }
        }
    }

    inner class HourlyListItem(private val listBinding: HourlyForecastListItemBinding) :
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
            Weather.CLOUDY -> R.drawable.sun_cloud_icon
            Weather.RAINY -> R.drawable.sun_behind_rain_cloud_icon
            Weather.NIGHT -> R.drawable.moon_icon
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ViewType.TITLE_FORECAST.value -> {
                val titleBinding = HourlyForecastTitleItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                HourlyTitleItem(titleBinding)
            }
            ViewType.HOURLY_FORECAST_LIST_ITEM.value -> {
                val listBinding = HourlyForecastListItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                HourlyListItem(listBinding)
            }
            else -> {
                TODO(error("onCreateViewHolder"))
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            ViewType.TITLE_FORECAST.value -> {
                (holder as HourlyTitleItem).bindTitleItem(list[position] as Forecast.TitleForecast)
            }
            ViewType.HOURLY_FORECAST_LIST_ITEM.value -> {
                (holder as HourlyListItem).bindListItem(list[position] as Forecast.HourlyForecastListItem)
            }
            else -> {
                TODO(error("onBindViewHolder"))
            }
        }
    }

    override fun getItemCount(): Int = list.size

    override fun getItemViewType(position: Int): Int =
        when (list[position]) {
            is Forecast.HourlyForecastListItem -> ViewType.HOURLY_FORECAST_LIST_ITEM.value
            is Forecast.TitleForecast -> ViewType.TITLE_FORECAST.value
        }

}