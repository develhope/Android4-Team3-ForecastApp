package co.develhope.meteoapp.ui.todayscreen.todayadapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import co.develhope.meteoapp.R
import co.develhope.meteoapp.databinding.HourlyForecastTitleItemBinding
import co.develhope.meteoapp.ui.todayscreen.Forecast
import org.threeten.bp.format.TextStyle
import java.util.*

class TitleViewHolder(private val titleBinding: HourlyForecastTitleItemBinding) :
    RecyclerView.ViewHolder(titleBinding.root) {
    @SuppressLint("SetTextI18n")
    fun bindTitleItem(item: Forecast.TitleForecast) {
        titleBinding.apply {
            cityAndRegionTextView.text = "${item.place.name}, " + item.place.region
            item.domainHourlyForecast.apply {
                currentDayTextView.text = itemView.context.getString(R.string.today)
                dateTextView.text = (
                        date.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault()).
                        replaceFirstChar { it.titlecase(Locale.getDefault()) }
                                + " " + date.dayOfMonth.toString() + " " +
                                date.month.getDisplayName(TextStyle.FULL, Locale.getDefault()).
                                replaceFirstChar { it.titlecase(Locale.getDefault()) }
                        )
            }
        }
    }
}