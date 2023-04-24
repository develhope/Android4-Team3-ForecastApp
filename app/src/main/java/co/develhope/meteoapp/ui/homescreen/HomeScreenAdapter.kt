package co.develhope.meteoapp.ui.homescreen

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.develhope.meteoapp.R
import co.develhope.meteoapp.data.domainmodel.Weather
import co.develhope.meteoapp.databinding.HomeScreenCardviewBinding
import co.develhope.meteoapp.databinding.HomeScreenSubtitleBinding
import co.develhope.meteoapp.databinding.HomeScreenTitleBinding
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.TextStyle
import java.util.*

interface OnCardClick {
    fun onCardClick(card: HomePageItems.SpecificDayWeather)
}
class HomeCardViewHolder(private val binding: HomeScreenCardviewBinding) : RecyclerView.
ViewHolder(binding.root) {

 @SuppressLint("SetTextI18n")
 fun bindHomeCardView(cardView: HomePageItems.SpecificDayWeather, listener: OnCardClick){
     binding.dayCard.text =
         when (cardView.homeCardWeather.date.dayOfWeek) {

         OffsetDateTime.now().dayOfWeek -> itemView.context.
         getString(R.string.today)

         OffsetDateTime.now().plusDays(1).dayOfWeek -> itemView.context.
         getString(R.string.tomorrow)

         else -> cardView.homeCardWeather.date.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault()).
         replaceFirstChar { it.titlecase(Locale.getDefault()) }
     }

     binding.dateCard.text = cardView.homeCardWeather.date.format(DateTimeFormatter.
     ofPattern("dd/MM", Locale.getDefault()))

     binding.iconWeather.setImageResource(when(cardView.homeCardWeather.weather){
         Weather.SUNNY -> R.drawable.sunny_icon
         Weather.MAINLY_CLEAR -> R.drawable.sun_behind_cloud
         Weather.PARTLY_CLOUDY -> R.drawable.sun_large_cloud_icon
         Weather.CLOUDY -> R.drawable.cloud
         Weather.FOG -> R.drawable.fog
         Weather.SUN_RAINY -> R.drawable.sun_behind_rain_cloud_icon
         Weather.RAINY -> R.drawable.cloud_with_rain
         Weather.SNOWFALL -> R.drawable.cloud_with_snow
         Weather.THUNDERSTORM -> R.drawable.cloud_with_lightning
         Weather.RAINY_THUNDERSTORM -> R.drawable.cloud_with_lightning_and_rain
         else -> throw java.lang.IllegalArgumentException("error")
     })

     binding.minDegrees.text = "${cardView.homeCardWeather.minDegree}°"

     binding.maxDegrees.text = "${cardView.homeCardWeather.maxDegree}°"

     binding.rainPerc.text = "${cardView.homeCardWeather.rainPerc}%"

     binding.windKmh.text = "${cardView.homeCardWeather.windKmh}kmh"

     binding.cardViewHomeScreen.setOnClickListener {
         listener.onCardClick(cardView)
         }
     }


}

class HomeTitleViewHolder(private val binding : HomeScreenTitleBinding) : RecyclerView.
ViewHolder(binding.root) {

    fun bindHomeTitle(title : HomePageItems.HomeTitle){
        binding.homeTitleCity.text = title.title.city
        binding.homeTitleRegion.text = title.title.region
    }
}

class HomeSubtitleViewHolder(private val binding : HomeScreenSubtitleBinding) : RecyclerView.
ViewHolder(binding.root) {

    fun bindHomesubtitle(subtitle : HomePageItems.NextDays){
        binding.homeSubtitle.text = itemView.context.getString(R.string.next_5_days)
    }
}

class HomeScreenAdapter(
    private val list: List<HomePageItems>,
    private val listener: OnCardClick,
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    enum class ViewType(val value: Int) {
        HOME_TITLE(1),
        HOME_CARD(2),
        HOME_NEXTDAYS(3)
    }

    override fun getItemViewType(position: Int): Int {
        return when (list[position]) {
            is HomePageItems.HomeTitle -> ViewType.HOME_TITLE.value
            is HomePageItems.SpecificDayWeather -> ViewType.HOME_CARD.value
            is HomePageItems.NextDays -> ViewType.HOME_NEXTDAYS.value
            else -> super.getItemViewType(position)
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
            is HomeTitleViewHolder -> holder.bindHomeTitle(list[position] as HomePageItems.HomeTitle)

            is HomeCardViewHolder -> holder.bindHomeCardView(list[position] as HomePageItems.SpecificDayWeather, listener )

            is HomeSubtitleViewHolder -> holder.bindHomesubtitle(list[position] as HomePageItems.NextDays)
        }
    }


    override fun getItemCount(): Int {
        return list.size
    }
}

