package co.develhope.meteoapp

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text


class HomeCardViewHolder(view:View): RecyclerView.ViewHolder(view) {

    val date : TextView
    val minDegree: TextView
    val maxDegree: TextView
    val windKmh: TextView
    val rainPerc: TextView
    val weather: ImageView

    init{
        date = view.findViewById(R.id.date_card)
        minDegree = view.findViewById(R.id.min_degrees)
        maxDegree = view.findViewById(R.id.max_degrees)
        windKmh = view.findViewById(R.id.wind_kmh)
        rainPerc = view.findViewById(R.id.rain_perc)
        weather = view.findViewById(R.id.icon_weather)
    }
}

class HomeScreenAdapter: RecyclerView.Adapter<HomeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}