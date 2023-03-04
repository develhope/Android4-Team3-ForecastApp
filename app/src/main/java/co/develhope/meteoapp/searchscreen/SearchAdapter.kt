package co.develhope.meteoapp.searchscreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import co.develhope.meteoapp.R
import co.develhope.meteoapp.data.GetHourlyForecastList
import co.develhope.meteoapp.data.HourlyForecast
import co.develhope.meteoapp.data.RecentSearches
import co.develhope.meteoapp.data.SearchBar

class SearchBarViewHolder(view: View) : RecyclerView.ViewHolder(view)

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
    val search: List<GetHourlyForecastList>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        when (viewType) {
            ViewType.HOURLYFORECAST.num -> {
                val listItem =
                    LayoutInflater.from(parent.context).inflate(R.layout.cities_list, parent, false)
                return HourlyForecastViewHolder(listItem)
            }
            ViewType.RESENTSEARCH.num -> {
                val listItem2 = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recent_search, parent, false)
                return RecentSearchViewHolder(listItem2)
            }
            ViewType.SEARCHBAR.num -> {
                val listItem3 =
                    LayoutInflater.from(parent.context).inflate(R.layout.search_bar, parent, false)
                return SearchBarViewHolder(listItem3)
            }
            else -> TODO()
        }
    }


    enum class ViewType(val num: Int) {
        HOURLYFORECAST(1),
        SEARCHBAR(2),
        RESENTSEARCH(3),
    }

    override fun getItemViewType(position: Int): Int {
        return when (search[position]) {

            is HourlyForecast -> ViewType.HOURLYFORECAST.num
            is RecentSearches -> ViewType.RESENTSEARCH.num
            is SearchBar -> ViewType.SEARCHBAR.num
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (search[position]) {
            is HourlyForecast -> {
                (holder as HourlyForecastViewHolder).weather.text =
                    (search[position] as HourlyForecast).weather.toString().lowercase()
                holder.itemView.isClickable
                holder.itemView.setOnClickListener {
                    val destination = SearchScreenDirections.searchScreenToHomeScreen()
                    destination.cityName = (search[position] as HourlyForecast).cities
                    destination.regionName = "Italia"
                    it.findNavController().navigate(destination)
                }
                holder.degrees.text =
                    "${(search[position] as HourlyForecast).degrees}°"
                holder.cities.text =
                    (search[position] as HourlyForecast).cities

            }
            is RecentSearches -> {
                (holder as RecentSearchViewHolder).recentSearches.text =
                    (search[position] as RecentSearches).recentSearches
            }
            is SearchBar -> {

            }
        }
    }

    override fun getItemCount(): Int {
        return search.size
    }
}




