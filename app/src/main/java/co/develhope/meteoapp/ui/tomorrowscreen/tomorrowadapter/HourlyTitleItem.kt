package co.develhope.meteoapp.ui.tomorrowscreen.tomorrowadapter

import androidx.recyclerview.widget.RecyclerView
import co.develhope.meteoapp.R
import co.develhope.meteoapp.databinding.HourlyForecastTitleItemBinding
import co.develhope.meteoapp.ui.tomorrowscreen.Forecast
import org.threeten.bp.format.TextStyle
import java.util.*

class HourlyTitleItem(private val titleBinding: HourlyForecastTitleItemBinding) :
    RecyclerView.ViewHolder(titleBinding.root) {
    fun bindTitleItem(item: Forecast.TitleForecast) {
        titleBinding.apply {
            (item.place.name + ", " + item.place.region).also {
                cityAndRegionTextView.text = it
            }
            currentDayTextView.text = itemView.context.getString(R.string.tomorrow)
            (item.domainHourlyForecast.date.dayOfWeek.getDisplayName(
                TextStyle.FULL,
                Locale.getDefault()
            ).replaceFirstChar { it.titlecase(Locale.getDefault()) } + " " +
                    item.domainHourlyForecast.date.dayOfMonth.toString() + " " +
                    item.domainHourlyForecast.date.month.getDisplayName(
                        TextStyle.FULL,
                        Locale.getDefault()
                    ).replaceFirstChar { it.titlecase(Locale.getDefault()) }
                    ).also { dateTextView.text = it }
        }
    }
}