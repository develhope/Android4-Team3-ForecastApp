package co.develhope.meteoapp.ui.searchscreen

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.develhope.meteoapp.R
import co.develhope.meteoapp.data.domainmodel.Place
import co.develhope.meteoapp.databinding.CitiesListBinding
import co.develhope.meteoapp.databinding.RecentSearchBinding


class CitiesViewHolder(private val binding: CitiesListBinding) :
    RecyclerView.ViewHolder(binding.root) {
    @SuppressLint("SetTextI18n")
    fun citiesCardView(cardView: GetCitiesList.Cities) {
        binding.citiesName.text = "${cardView.city.name},"
        binding.region.text = cardView.city.region
    }
}

class RecentSearchViewHolder(private val binding: RecentSearchBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun recentSearchesView() {
        binding.recentSearch.text = itemView.context.getString(R.string.recent_searches)
    }
}

class SearchAdapter(
    private val search: List<GetCitiesList>,
    private val onClick: (Place) -> Unit

) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {
            ViewType.CITIESLIST.num -> {
                CitiesViewHolder(
                    CitiesListBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            ViewType.RESENTSEARCH.num -> {
                RecentSearchViewHolder(
                    RecentSearchBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            else -> {
                TODO()
            }
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

        when (holder) {
            is CitiesViewHolder -> {
                holder.citiesCardView(search[position] as GetCitiesList.Cities)
                holder.itemView.setOnClickListener {
                    onClick((search[position] as GetCitiesList.Cities).city)
                }
            }
            is RecentSearchViewHolder -> {
                holder.recentSearchesView()
            }
        }
    }

    override fun getItemCount(): Int {
        return search.size
    }
}








