package co.develhope.meteoapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SearchBarViewHolder(view: View) : RecyclerView.ViewHolder(view) {


}

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

        when (viewType){
            ViewType.HOURLYFORECAST.num -> {
                    val listItem =
                        LayoutInflater.from(parent.context).inflate(R.layout.cities_list, parent, false)
                    return HourlyForecastViewHolder(listItem)
                }
            ViewType.RESENTSEARCH.num -> {
                val listItem2 = LayoutInflater.from(parent.context).inflate(R.layout.recent_search, parent, false)
                return RecentSearchViewHolder(listItem2)
            }
            ViewType.SEARCHBAR.num -> {
                val listItem3 = LayoutInflater.from(parent.context).inflate(R.layout.search_bar, parent, false)
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
            is RecentSearches -> ViewType.SEARCHBAR.num
            is SearchBar -> ViewType.RESENTSEARCH.num
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as HourlyForecastViewHolder).weather.text =
            (search[position] as HourlyForecast).weather.toString()
        (holder as HourlyForecastViewHolder).degrees.text =
            (search[position] as HourlyForecast).degrees.toString()
        (holder as HourlyForecastViewHolder).cities.text =
            (search[position] as HourlyForecast).cities.toString()


        when (search[position]){
            is HourlyForecast -> {
                (holder as HourlyForecastViewHolder).weather.text =
                    (search[position] as HourlyForecast).weather.toString()
                (holder as HourlyForecastViewHolder).degrees.text =
                    (search[position] as HourlyForecast).degrees.toString()
                (holder as HourlyForecastViewHolder).cities.text =
                    (search[position] as HourlyForecast).cities.toString()
            }
            is RecentSearches -> {
                (holder as RecentSearchViewHolder).recentSearches.text =
                    (search[position] as RecentSearches).recentSearches.toString()
            }
            is SearchBar -> {

            }
        }
    }

    override fun getItemCount(): Int {
        return search.size
    }
}




