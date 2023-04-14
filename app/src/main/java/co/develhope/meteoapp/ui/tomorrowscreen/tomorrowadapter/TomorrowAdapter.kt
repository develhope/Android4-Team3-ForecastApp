package co.develhope.meteoapp.ui.tomorrowscreen.tomorrowadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import co.develhope.meteoapp.R
import co.develhope.meteoapp.databinding.HourlyForecastListItemBinding
import co.develhope.meteoapp.databinding.HourlyForecastTitleItemBinding
import co.develhope.meteoapp.ui.tomorrowscreen.Forecast

class TomorrowAdapter : ListAdapter<Forecast, RecyclerView.ViewHolder>(ItemDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            ViewType.TITLE_FORECAST.value -> {
                val titleBinding = HourlyForecastTitleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                HourlyTitleItem(titleBinding)
            }
            ViewType.HOURLY_FORECAST_LIST_ITEM.value -> {
                val listBinding = HourlyForecastListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                HourlyListItem(listBinding)
            }
            else -> throw java.lang.IllegalArgumentException("error")
        }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            ViewType.TITLE_FORECAST.value -> {
                (holder as HourlyTitleItem).bindTitleItem(currentList[position] as Forecast.TitleForecast)
            }
            ViewType.HOURLY_FORECAST_LIST_ITEM.value -> {
                (holder as HourlyListItem).bindListItem(currentList[position] as Forecast.HourlyForecastListItem)
            }
            else -> throw java.lang.IllegalArgumentException("error")
        }
        holder.setIsRecyclable(true)
    }
    override fun getItemCount(): Int = currentList.size
    override fun getItemViewType(position: Int): Int = when (currentList[position]) {
        is Forecast.HourlyForecastListItem -> ViewType.HOURLY_FORECAST_LIST_ITEM.value
        is Forecast.TitleForecast -> ViewType.TITLE_FORECAST.value
    }
    private enum class ViewType(val value: Int) {
        TITLE_FORECAST(R.layout.hourly_forecast_title_item),
        HOURLY_FORECAST_LIST_ITEM(R.id.hourly_forecast_list_item_view)
    }
    class ItemDiffCallback: DiffUtil.ItemCallback<Forecast>() {
        override fun areItemsTheSame(oldItem: Forecast, newItem: Forecast): Boolean = (oldItem.id == newItem.id)
        override fun areContentsTheSame(oldItem: Forecast, newItem: Forecast): Boolean = (oldItem == newItem)
    }
}