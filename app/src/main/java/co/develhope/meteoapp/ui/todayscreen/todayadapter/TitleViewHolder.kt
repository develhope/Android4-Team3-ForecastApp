package co.develhope.meteoapp.ui.todayscreen.todayadapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import co.develhope.meteoapp.R

class TitleViewHolder(view: View): RecyclerView.ViewHolder(view){
    val cityAndRegionTextView: TextView
    val currentDayTextView: TextView
    val dateTextView: TextView

    init {
        cityAndRegionTextView = view.findViewById(R.id.city_and_region_text_view)
        currentDayTextView = view.findViewById(R.id.current_day_text_view)
        dateTextView = view.findViewById(R.id.date_text_view)
    }
}