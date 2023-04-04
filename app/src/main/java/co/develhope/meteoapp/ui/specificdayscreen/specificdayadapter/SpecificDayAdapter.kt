package co.develhope.meteoapp.ui.specificdayscreen.specificdayadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.develhope.meteoapp.databinding.HourlyForecastListItemBinding
import co.develhope.meteoapp.databinding.HourlyForecastTitleItemBinding
import co.develhope.meteoapp.ui.specificdayscreen.Forecast

class SpecificDayAdapter(private val list: List<Forecast>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
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
            else -> throw java.lang.IllegalArgumentException("error")
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
            else -> throw java.lang.IllegalArgumentException("error")
        }
    }
    override fun getItemCount(): Int = list.size
    override fun getItemViewType(position: Int): Int =
        when (list[position]) {
            is Forecast.HourlyForecastListItem -> ViewType.HOURLY_FORECAST_LIST_ITEM.value
            is Forecast.TitleForecast -> ViewType.TITLE_FORECAST.value
        }
    private enum class ViewType(val value: Int) {
        TITLE_FORECAST(1),
        HOURLY_FORECAST_LIST_ITEM(2)
    }
}