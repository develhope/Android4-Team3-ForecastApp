package co.develhope.meteoapp.ui.searchscreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import co.develhope.meteoapp.R
import co.develhope.meteoapp.data.domainmodel.Place


class HourlyForecastViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val weather: TextView
    val degrees: TextView
    val cities: TextView


    init {
        weather = view.findViewById(R.id.weather)
        degrees = view.findViewById(R.id.degrees)
        cities = view.findViewById(R.id.cities_name)
    }

}

class RecentSearchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val recentSearches: TextView

    init {
        recentSearches = view.findViewById(R.id.recent_search)
    }
}

class SearchAdapter(
    private val search: List<GetCitiesList>,
    private val onClick: (Place) -> Unit

) : RecyclerView.Adapter<RecyclerView.ViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {
            ViewType.HOURLYFORECAST.num -> {
                val listItem =
                    LayoutInflater.from(parent.context).inflate(R.layout.cities_list, parent, false)
                HourlyForecastViewHolder(listItem)
            }
            ViewType.RESENTSEARCH.num -> {
                val listItem2 = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recent_search, parent, false)
                RecentSearchViewHolder(listItem2)
            }
            else -> TODO()
        }
    }


    enum class ViewType(val num: Int) {
        HOURLYFORECAST(1),
        RESENTSEARCH(2)
    }

    override fun getItemViewType(position: Int): Int {
        return when (search[position]) {

            is GetCitiesList.Cities -> ViewType.HOURLYFORECAST.num
            is GetCitiesList.RecentSearches -> ViewType.RESENTSEARCH.num
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (search[position]) {
            is GetCitiesList.Cities -> {
                (holder as HourlyForecastViewHolder).weather.text =
                    (search[position] as GetCitiesList.Cities).weather.toString().lowercase()
                holder.itemView.setOnClickListener {
                    onClick((search[position] as GetCitiesList.Cities).city)

                }
                holder.degrees.text = "${(search[position] as GetCitiesList.Cities).degrees}°"
                holder.cities.text =
                    (search[position] as GetCitiesList.Cities).city.name

            }
            is GetCitiesList.RecentSearches -> {
                (holder as RecentSearchViewHolder).recentSearches.text =
                    (search[position] as GetCitiesList.RecentSearches).recentSearches
            }
        }
        }

    override fun getItemCount(): Int {
        return search.size
    }




}








