package co.develhope.meteoapp.ui.todayscreen.todayadapter

import android.annotation.SuppressLint
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import co.develhope.meteoapp.R
import co.develhope.meteoapp.data.domainmodel.Weather
import co.develhope.meteoapp.ui.todayscreen.Forecast
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.format.TextStyle
import java.util.*

class TodayAdapter(private val list: List<Forecast>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {
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
            else -> throw java.lang.IllegalArgumentException("error")
        }
    }
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(list[position]){
            is Forecast.TitleForecast ->{
                (holder as TitleViewHolder).apply {
                    cityAndRegionTextView.text =
                        (list[position] as Forecast.TitleForecast).place.name + ", " +
                                (list[position] as Forecast.TitleForecast).place.region

                    if(OffsetDateTime.now().dayOfMonth ==
                        (list[position] as Forecast.TitleForecast).domainHourlyForecast.date.dayOfMonth){
                        currentDayTextView.text = itemView.context.getString(R.string.today)
                    } else if (OffsetDateTime.now().plusDays(1).dayOfMonth ==
                        (list[position] as Forecast.TitleForecast).domainHourlyForecast.date.dayOfMonth) {
                        currentDayTextView.text = itemView.context.getString(R.string.tomorrow)
                    } else {
                        currentDayTextView.text =
                            (list[position] as Forecast.TitleForecast).domainHourlyForecast.date.dayOfWeek.
                            getDisplayName(TextStyle.FULL, Locale.getDefault()).
                            replaceFirstChar { it.titlecase(Locale.getDefault()) }
                    }
                    dateTextView.text =
                        (list[position] as Forecast.TitleForecast).domainHourlyForecast.date.dayOfWeek.
                        getDisplayName(TextStyle.FULL, Locale.getDefault()).
                        replaceFirstChar { it.titlecase(Locale.getDefault()) } +
                                " " +
                                (list[position] as Forecast.TitleForecast).domainHourlyForecast.date.dayOfMonth +
                                " " +
                                (list[position] as Forecast.TitleForecast).domainHourlyForecast.date.month.
                                getDisplayName(TextStyle.FULL, Locale.getDefault()).
                                replaceFirstChar { it.titlecase(Locale.getDefault()) }
                }
            }
            is Forecast.HourlyForecastListItem ->{
                (holder as HourlyViewHolder).apply {
                    hourTextView.text =
                        "${(list[position] as Forecast.HourlyForecastListItem).domainHourlyForecast.date.hour}:00"
                    when((list[position] as Forecast.HourlyForecastListItem).domainHourlyForecast.detailedCardForecast.weather){
                        Weather.SUNNY -> iconView.setImageResource(R.drawable.sunny_icon)
                        Weather.CLOUDY -> iconView.setImageResource(R.drawable.sun_cloud_icon)
                        Weather.RAINY -> iconView.setImageResource(R.drawable.sun_behind_rain_cloud_icon)
                        Weather.NIGHT -> iconView.setImageResource(R.drawable.moon_icon)
                    }
                    celsiusTextView.text =
                        "${(list[position] as Forecast.HourlyForecastListItem).domainHourlyForecast.detailedCardForecast.celsius}°"
                    wetnessTextView.text =
                        "${(list[position] as Forecast.HourlyForecastListItem).domainHourlyForecast.detailedCardForecast.wetness}%"

                    //Card Values From here
                    cardPerceivedTemperatureView.text =
                        "${(list[position] as Forecast.HourlyForecastListItem).domainHourlyForecast.detailedCardForecast.perceivedTemperature}°"
                    cardCoverageTextView.text =
                        "${(list[position] as Forecast.HourlyForecastListItem).domainHourlyForecast.detailedCardForecast.coverage}%"
                    cardRainTextView.text =
                        "${(list[position] as Forecast.HourlyForecastListItem).domainHourlyForecast.detailedCardForecast.rain}cm"
                    cardWetnessTextView.text =
                        "${(list[position] as Forecast.HourlyForecastListItem).domainHourlyForecast.detailedCardForecast.wetness}%"
                    cardUvIndexTextView.text =
                        "${(list[position] as Forecast.HourlyForecastListItem).domainHourlyForecast.detailedCardForecast.uvIndex}/10"
                    cardWindTextView.text =
                        "SSE${(list[position] as Forecast.HourlyForecastListItem).domainHourlyForecast.detailedCardForecast.wind}km/h"

                    //this implementation enable the expandable card view for each list item
                    val wholeView = itemView
                        .findViewById<LinearLayout>(R.id.hourly_forecast_list_item_view)
                    val arrowButton = itemView
                        .findViewById<ImageView>(R.id.expandable_button_image_view)
                    val lineView = itemView
                        .findViewById<ImageView>(R.id.line_image_view)
                    val hiddenCard = itemView
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
    }
    override fun getItemCount(): Int {
        return list.size
    }
    private enum class ViewType(val value: Int){
        TITLE_FORECAST(1),
        HOURLY_FORECAST_LIST_ITEM(2)
    }
    override fun getItemViewType(position: Int): Int =
        when(list[position]){
            is Forecast.HourlyForecastListItem -> ViewType.HOURLY_FORECAST_LIST_ITEM.value
            is Forecast.TitleForecast -> ViewType.TITLE_FORECAST.value
        }
}