package co.develhope.meteoapp.ui.searchscreen

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import co.develhope.meteoapp.R
import co.develhope.meteoapp.data.domainmodel.Place


class CitiesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val region: TextView
    val cities: TextView


    init {
        region = view.findViewById(R.id.region)
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

) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {
            ViewType.CITIESLIST.num -> {
                val listItem =
                    LayoutInflater.from(parent.context).inflate(R.layout.cities_list, parent, false)
                CitiesViewHolder(listItem)
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
        CITIESLIST(1),
        RESENTSEARCH(2)
    }

    override fun getItemViewType(position: Int): Int {
        return when (search[position]) {

            is GetCitiesList.Cities -> ViewType.CITIESLIST.num
            is GetCitiesList.RecentSearches -> ViewType.RESENTSEARCH.num
        }

    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (search[position]) {
            is GetCitiesList.Cities -> {
                (holder as CitiesViewHolder).cities.text =
                    "${(search[position] as GetCitiesList.Cities).city.name},"
                holder.region.text =
                    (search[position] as GetCitiesList.Cities).city.region
                holder.itemView.setOnClickListener {
                    onClick((search[position] as GetCitiesList.Cities).city)
                }
            }
            is GetCitiesList.RecentSearches -> {
                (holder as RecentSearchViewHolder).recentSearches.text = holder.itemView.context.getString(R.string.recent_searches)
            }
        }
    }

    override fun getItemCount(): Int {
        return search.size
    }
}








