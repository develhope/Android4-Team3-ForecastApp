package co.develhope.meteoapp

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import co.develhope.meteoapp.databinding.HomeScreenCardviewBinding
import co.develhope.meteoapp.databinding.HomeScreenSubtitleBinding
import co.develhope.meteoapp.databinding.HomeScreenTitleBinding
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.TextStyle
import java.util.*

class HomeCardViewHolder(private val binding: HomeScreenCardviewBinding) : RecyclerView.
ViewHolder(binding.root) {

 @SuppressLint("SetTextI18n")
 fun bindHomeCardView(cardView: SpecificDayWeather){
     binding.dayCard.text =
         when (cardView.date.dayOfWeek) {

         OffsetDateTime.now().dayOfWeek -> itemView.context.
         getString(R.string.today)

         OffsetDateTime.now().plusDays(1).dayOfWeek -> itemView.context.
         getString(R.string.tomorrow)

         else -> cardView.date.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault()).
         replaceFirstChar { it.titlecase(Locale.getDefault()) }
     }

     binding.dateCard.text = cardView.date.format(DateTimeFormatter.
     ofPattern("dd/MM", Locale.getDefault()))

     binding.iconWeather.setImageResource(when(cardView.weather){
         Weather.SUNNY -> R.drawable.sunny_icon
         Weather.CLOUDY -> R.drawable.sun_cloud_icon
         Weather.RAINY -> R.drawable.sun_behind_rain_cloud_icon
         else -> throw java.lang.IllegalArgumentException("error")
     })

     binding.minDegrees.text = "${cardView.minDegree}°"

     binding.maxDegrees.text = "${cardView.maxDegree}°"

     binding.rainPerc.text = "${cardView.rainPerc}%"

     binding.windKmh.text = "${cardView.windKmh}kmh"

     binding.cardViewHomeScreen.setOnClickListener { when(cardView.date.dayOfWeek){
         OffsetDateTime.now().dayOfWeek ->  it.findNavController().
         navigate(R.id.homeScreen_to_todayScreen)
         else ->  it.findNavController().navigate(R.id.homeScreen_to_specificDayScreen)
         }
     }

 }
}

class HomeTitleViewHolder(private val binding : HomeScreenTitleBinding) : RecyclerView.
ViewHolder(binding.root) {

    fun bindHomeTitle(title : HomeTitle, args: HomeScreenArgs){
        binding.homeTitleCity.text = args.cityName
        binding.homeTitleRegion.text = args.regionName
    }
}

class HomeSubtitleViewHolder(private val binding : HomeScreenSubtitleBinding) : RecyclerView.
ViewHolder(binding.root) {

    fun bindHomesubtitle(subtitle : NextDays){
        binding.homeSubtitle.text = itemView.context.getString(R.string.next_5_days)
    }
}

class HomeScreenAdapter(val list: List<HomePageItems>, val args: HomeScreenArgs) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    enum class ViewType(val value: Int) {
        HOME_TITLE(1),
        HOME_CARD(2),
        HOME_NEXTDAYS(3)
    }

    override fun getItemViewType(position: Int): Int {
        return when (list[position]) {
            is HomeTitle -> ViewType.HOME_TITLE.value
            is SpecificDayWeather -> ViewType.HOME_CARD.value
            is NextDays -> ViewType.HOME_NEXTDAYS.value
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
       return when (viewType) {
            ViewType.HOME_TITLE.value -> HomeTitleViewHolder(HomeScreenTitleBinding.
            inflate(LayoutInflater.from(parent.context), parent,false))

            ViewType.HOME_CARD.value -> HomeCardViewHolder(HomeScreenCardviewBinding.
            inflate(LayoutInflater.from(parent.context), parent,false))

           ViewType.HOME_NEXTDAYS.value -> HomeSubtitleViewHolder(HomeScreenSubtitleBinding.
            inflate(
                LayoutInflater.from(parent.context), parent,false))
            else -> throw java.lang.IllegalArgumentException("error")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HomeTitleViewHolder -> holder.bindHomeTitle(list[position] as HomeTitle, args)

            is HomeCardViewHolder -> holder.bindHomeCardView(list[position] as SpecificDayWeather)

            is HomeSubtitleViewHolder -> holder.bindHomesubtitle(list[position] as NextDays)
        }
    }


    override fun getItemCount(): Int {
        return list.size
    }
}

