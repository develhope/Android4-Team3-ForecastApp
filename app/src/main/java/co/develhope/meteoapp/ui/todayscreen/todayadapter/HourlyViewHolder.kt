package co.develhope.meteoapp.ui.todayscreen.todayadapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import co.develhope.meteoapp.R

class HourlyViewHolder(view: View): RecyclerView.ViewHolder(view){
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