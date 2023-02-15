package co.develhope.meteoapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.threeten.bp.OffsetDateTime

class HourlyViewHolder(view: View):RecyclerView.ViewHolder(view){
    val hourTextView: TextView
    val iconView: ImageView
    val celsiusTextView: TextView
    val wetnessTextView: TextView

    init {
        hourTextView = view.findViewById(R.id.hour_list_item_view)
        iconView = view.findViewById(R.id.icon_list_item_view)
        celsiusTextView = view.findViewById(R.id.celsius_list_item_view)
        wetnessTextView = view.findViewById(R.id.wetness_list_item_view)
    }
}

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

class CardViewHolder(view: View):RecyclerView.ViewHolder(view){
    val perceivedTemperatureView: TextView
    val wetnessTextView: TextView
    val coverageTextView: TextView
    val uvIndexTextView: TextView
    val windTextView: TextView
    val rainTextView: TextView

    init {
        perceivedTemperatureView = view.findViewById(R.id.perceived_temperature_text_view)
        wetnessTextView = view.findViewById(R.id.wetness_text_view)
        coverageTextView = view.findViewById(R.id.coverage_text_view)
        uvIndexTextView = view.findViewById(R.id.uv_index_text_view)
        windTextView = view.findViewById(R.id.wind_text_view)
        rainTextView = view.findViewById(R.id.rain_text_view)
    }
}

class Adapter(val list: List<Forecast>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var cardViewHolder: CardViewHolder
    private lateinit var hourlyViewHolder: HourlyViewHolder

    enum class ViewType(val value: Int){
        TITLE_FORECAST(1),
        HOURLY_FORECAST(2),
        CARD_FORECAST(3),
    }
    override fun getItemViewType(position: Int): Int =
        when(list[position]){
            is DetailedCardForecast -> ViewType.CARD_FORECAST.value
            is HourlyForecast -> ViewType.HOURLY_FORECAST.value
            is TitleForecast -> ViewType.TITLE_FORECAST.value
            //TODO REMOVE THIS IMPLEMENTATION FOR A BETTER ONE
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        when(viewType){
            ViewType.TITLE_FORECAST.value -> {
                val ListItemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.hourly_forecast_title_item, parent, false)
                return TitleViewHolder(ListItemView)
            }
            ViewType.HOURLY_FORECAST.value -> {
                val ListItemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.hourly_forecast_list_item, parent, false)
                return HourlyViewHolder(ListItemView)
            }
            ViewType.CARD_FORECAST.value ->{
                val ListItemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.hourly_forecast_card_view, parent, false)
                return CardViewHolder(ListItemView)
            }
            else ->{
                TODO()
            }
        }
    }
    /*
    val ListItemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.hourly_forecast_list_item, parent, false)
            return HourlyViewHolder(hourlyListItemView)
            */

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(list[position]){
            is HourlyForecast ->{
                (holder as HourlyViewHolder).hourTextView.text =
                    (list[position] as HourlyForecast).date.hour.toString()
                when((list[position] as HourlyForecast).weather){
                    Weather.SUNNY -> (holder as HourlyViewHolder).iconView.setImageResource(R.drawable.sunny_icon)
                    Weather.CLOUD -> (holder as HourlyViewHolder).iconView.setImageResource(R.drawable.sun_cloud_icon)
                    Weather.RAIN -> (holder as HourlyViewHolder).iconView.setImageResource(R.drawable.sun_behind_rain_cloud_icon)
                    Weather.NIGHT -> (holder as HourlyViewHolder).iconView.setImageResource(R.drawable.moon_icon)
                }
                (holder as HourlyViewHolder).celsiusTextView.text =
                    (list[position] as HourlyForecast).celsius.toString()
                (holder as HourlyViewHolder).wetnessTextView.text =
                    (list[position] as HourlyForecast).wetness.toString()
            }
            is DetailedCardForecast ->{

            }
            is TitleForecast ->{

                (holder as TitleViewHolder).cityAndRegionTextView.text =
                    "${(list[position] as TitleForecast).city}, " +
                            "${(list[position] as TitleForecast).region}"

                if(OffsetDateTime.now().dayOfMonth ==
                    (list[position] as TitleForecast).date.dayOfMonth){
                    (holder as TitleViewHolder).currentDayTextView.text = "Today"
                }
                else{
                    (holder as TitleViewHolder).currentDayTextView.text = "NotToday"
                }

                (holder as TitleViewHolder).dateTextView.text =
                    ("${(list[position] as TitleForecast).date.dayOfWeek} " +
                            "${(list[position] as TitleForecast).date.dayOfMonth}" +
                            "${(list[position] as TitleForecast).date.month}").lowercase()
            }
        }
    }
}