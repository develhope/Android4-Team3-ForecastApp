package co.develhope.meteoapp.ui.todayscreen

import android.annotation.SuppressLint
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import co.develhope.meteoapp.R
import co.develhope.meteoapp.data.domainmodel.Weather
import org.threeten.bp.OffsetDateTime

class TitleViewHolder(view: View):RecyclerView.ViewHolder(view){
    val cityAndRegionTextView: TextView
    val currentDayTextView: TextView
    val dateTextView: TextView

    init {
        cityAndRegionTextView = view.findViewById(R.id.city_and_region_text_view)
        currentDayTextView = view.findViewById(R.id.current_day_text_view)
        dateTextView = view.findViewById(R.id.date_text_view)
    }
}

class HourlyViewHolder(view: View):RecyclerView.ViewHolder(view){
    val hourTextView: TextView
    val iconView: ImageView
    val celsiusTextView: TextView
    val wetnessTextView: TextView

    val cardPerceivedTemperatureView: TextView
    val cardWetnessTextView: TextView
    val cardCoverageTextView: TextView
    val cardUvIndexTextView: TextView
    val cardWindTextView: TextView
    val cardRainTextView: TextView

    init {
        hourTextView = view.findViewById(R.id.hour_list_item_view)
        iconView = view.findViewById(R.id.icon_list_item_view)
        celsiusTextView = view.findViewById(R.id.celsius_list_item_view)
        wetnessTextView = view.findViewById(R.id.wetness_list_item_view)

        cardPerceivedTemperatureView = view.findViewById(R.id.perceived_temperature_text_view)
        cardWetnessTextView = view.findViewById(R.id.wetness_text_view)
        cardCoverageTextView = view.findViewById(R.id.coverage_text_view)
        cardUvIndexTextView = view.findViewById(R.id.uv_index_text_view)
        cardWindTextView = view.findViewById(R.id.wind_text_view)
        cardRainTextView = view.findViewById(R.id.rain_text_view)
    }
}


class TodayAdapter(private val list: List<Forecast>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private enum class ViewType(val value: Int){
        TITLE_FORECAST(1),
        HOURLY_FORECAST_LIST_ITEM(2)
    }

    override fun getItemViewType(position: Int): Int =
        when(list[position]){
            is Forecast.HourlyForecastListItem -> ViewType.HOURLY_FORECAST_LIST_ITEM.value
            is Forecast.TitleForecast -> ViewType.TITLE_FORECAST.value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            ViewType.TITLE_FORECAST.value -> {
                val listItemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.hourly_forecast_title_item, parent, false)
                TitleViewHolder(listItemView)
            }
            ViewType.HOURLY_FORECAST_LIST_ITEM.value -> {
                val listItemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.hourly_forecast_list_item, parent, false)
                HourlyViewHolder(listItemView)
            }
            else ->{
                TODO()
            }
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(list[position]){

            is Forecast.TitleForecast ->{
                (holder as TitleViewHolder).cityAndRegionTextView.text =
                    "${(list[position] as Forecast.TitleForecast).place.name}, " +
                            "${(list[position] as Forecast.TitleForecast).place.region}"

                if(OffsetDateTime.now().dayOfMonth ==
                    (list[position] as Forecast.TitleForecast).domainHourlyForecast.date.dayOfMonth){
                    (holder as TitleViewHolder).currentDayTextView.text = "Today"
                } else if (OffsetDateTime.now().plusDays(1).dayOfMonth ==
                    (list[position] as Forecast.TitleForecast).domainHourlyForecast.date.dayOfMonth) {
                    (holder as TitleViewHolder).currentDayTextView.text = "Tomorrow"
                } else {
                    (holder as TitleViewHolder).currentDayTextView.text =
                        (list[position] as Forecast.TitleForecast).domainHourlyForecast.date.dayOfWeek.name
                            .lowercase().replaceFirstChar { it.titlecase() }
                }
                (holder as TitleViewHolder).dateTextView.text =
                    ("${(list[position] as Forecast.TitleForecast).domainHourlyForecast.date.dayOfWeek}" + " " +
                            "${(list[position] as Forecast.TitleForecast).domainHourlyForecast.date.dayOfMonth}" + " " +
                            "${(list[position] as Forecast.TitleForecast).domainHourlyForecast.date.month}").lowercase()
            }

            is Forecast.HourlyForecastListItem ->{
                (holder as HourlyViewHolder).hourTextView.text =
                    "${(list[position] as Forecast.HourlyForecastListItem).domainHourlyForecast.date.hour.toString()}:00"
                when((list[position] as Forecast.HourlyForecastListItem).domainHourlyForecast.detailedCardForecast.weather){
                    Weather.SUNNY -> (holder as HourlyViewHolder).iconView.setImageResource(R.drawable.sunny_icon)
                    Weather.CLOUDY -> (holder as HourlyViewHolder).iconView.setImageResource(R.drawable.sun_cloud_icon)
                    Weather.RAINY -> (holder as HourlyViewHolder).iconView.setImageResource(R.drawable.sun_behind_rain_cloud_icon)
                    Weather.NIGHT -> (holder as HourlyViewHolder).iconView.setImageResource(R.drawable.moon_icon)
                }
                (holder as HourlyViewHolder).celsiusTextView.text =
                    "${(list[position] as Forecast.HourlyForecastListItem).domainHourlyForecast.detailedCardForecast.celsius}°"
                (holder as HourlyViewHolder).wetnessTextView.text =
                    "${(list[position] as Forecast.HourlyForecastListItem).domainHourlyForecast.detailedCardForecast.wetness}%"

                //Card Values From here
                (holder as HourlyViewHolder).cardPerceivedTemperatureView.text =
                    "${(list[position] as Forecast.HourlyForecastListItem).domainHourlyForecast.detailedCardForecast.perceivedTemperature.toString()}°"
                (holder as HourlyViewHolder).cardCoverageTextView.text =
                    "${(list[position] as Forecast.HourlyForecastListItem).domainHourlyForecast.detailedCardForecast.coverage.toString()}%"
                (holder as HourlyViewHolder).cardRainTextView.text =
                    "${(list[position] as Forecast.HourlyForecastListItem).domainHourlyForecast.detailedCardForecast.rain.toString()}cm"
                (holder as HourlyViewHolder).cardWetnessTextView.text =
                    "${(list[position] as Forecast.HourlyForecastListItem).domainHourlyForecast.detailedCardForecast.wetness.toString()}%"
                (holder as HourlyViewHolder).cardUvIndexTextView.text =
                    "${(list[position] as Forecast.HourlyForecastListItem).domainHourlyForecast.detailedCardForecast.uvIndex.toString()}/10"
                (holder as HourlyViewHolder).cardWindTextView.text =
                    "SSE${(list[position] as Forecast.HourlyForecastListItem).domainHourlyForecast.detailedCardForecast.wind.toString()}km/h"

                //this implementation enable the expandable card view for each list item
                val wholeView = (holder as HourlyViewHolder).itemView
                    .findViewById<LinearLayout>(R.id.hourly_forecast_list_item_view)
                val arrowButton = (holder as HourlyViewHolder).itemView
                    .findViewById<ImageView>(R.id.expandable_button_image_view)
                val lineView = (holder as HourlyViewHolder).itemView
                    .findViewById<ImageView>(R.id.line_image_view)
                val hiddenCard = (holder as HourlyViewHolder).itemView
                    .findViewById<CardView>(R.id.hidden_hourly_forecast_list_item_view)

                //expandable card view implementation
                arrowButton.setOnClickListener {
                    if (hiddenCard.visibility == View.VISIBLE) {
                        TransitionManager.beginDelayedTransition(wholeView, AutoTransition())
                        lineView.visibility = View.VISIBLE
                        hiddenCard.visibility = View.GONE
                        arrowButton.setImageResource(R.drawable.baseline_keyboard_arrow_down_24)
                    } else {
                        TransitionManager.beginDelayedTransition(wholeView, AutoTransition())
                        lineView.visibility = View.GONE
                        hiddenCard.visibility = View.VISIBLE
                        arrowButton.setImageResource(R.drawable.baseline_keyboard_arrow_up_24)
                    }
                }
                //TODO remove baseline_keyboard_arrow_down_24 baseline_keyboard_arrow_up_24
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}