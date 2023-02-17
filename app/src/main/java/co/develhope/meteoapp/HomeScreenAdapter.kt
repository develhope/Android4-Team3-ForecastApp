package co.develhope.meteoapp

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.ZoneOffset
import org.threeten.bp.format.DateTimeFormatter
import org.w3c.dom.Text
import java.util.*


class HomeCardViewHolder(view:View): RecyclerView.ViewHolder(view) {

    val dayOfWeek : TextView
    val date : TextView
    val minDegree: TextView
    val maxDegree: TextView
    val windKmh: TextView
    val rainPerc: TextView
    val weather: ImageView

    init{
        dayOfWeek = view.findViewById(R.id.day_card)
        date = view.findViewById(R.id.date_card)
        minDegree = view.findViewById(R.id.min_degrees)
        maxDegree = view.findViewById(R.id.max_degrees)
        windKmh = view.findViewById(R.id.wind_kmh)
        rainPerc = view.findViewById(R.id.rain_perc)
        weather = view.findViewById(R.id.icon_weather)
    }
}

class HomeTitleViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val city : TextView
    val region : TextView

    init {
        city = view.findViewById(R.id.home_title_city)
        region = view.findViewById(R.id.home_title_region)
    }
}

class HomeSubtitleViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val nextFiveDays : TextView

    init {
        nextFiveDays = view.findViewById(R.id.home_subtitle)
    }
}

class HomeScreenAdapter(val list: List<HomePageItems>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    enum class ViewType(val value: Int){
        HOME_TITLE(1),
        HOME_CARD(2),
        HOME_NEXTDAYS(3)
    }

    override fun getItemViewType(position: Int): Int {
        return when(list[position]){
            is HomeTitle -> ViewType.HOME_TITLE.value
            is SpecificDayWeather -> ViewType.HOME_CARD.value
            is NextDays -> ViewType.HOME_NEXTDAYS.value
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when(viewType){
            ViewType.HOME_TITLE.value -> {
                val homeListItemView = LayoutInflater.from(parent.context).
                inflate(R.layout.home_screen_title, parent, false)
                return HomeTitleViewHolder(homeListItemView)
            }
            ViewType.HOME_CARD.value -> {
                val homeListItemView = LayoutInflater.from(parent.context).
                inflate(R.layout.home_screen_cardview, parent, false)
                return HomeCardViewHolder(homeListItemView)
            }
            ViewType.HOME_NEXTDAYS.value -> {
                val homeListItemView = LayoutInflater.from(parent.context).
                inflate(R.layout.home_screen_subtitle, parent, false)
                return HomeSubtitleViewHolder(homeListItemView)
            }
            else -> throw java.lang.IllegalArgumentException("error")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(list[position]){
            is HomeTitle -> {

                (holder as HomeTitleViewHolder).city.text =
                    (list[position] as HomeTitle).city

                (holder as HomeTitleViewHolder).region.text =
                    (list[position] as HomeTitle).region

            }

            is SpecificDayWeather -> {
                when ((list[position] as SpecificDayWeather).cardDayOfWeek){

                    OffsetDateTime.now() ->
                        (holder as HomeCardViewHolder).dayOfWeek.setText(R.string.today)

                    OffsetDateTime.now().plusDays(1) ->
                        (holder as HomeCardViewHolder).dayOfWeek.setText(R.string.tomorrow)

                    else -> (holder as HomeCardViewHolder).dayOfWeek.text =
                        "${(list[position] as SpecificDayWeather).cardDayOfWeek.dayOfWeek}"
                }

                (holder as HomeCardViewHolder).date.text =
                    (list[position] as SpecificDayWeather).date.format(DateTimeFormatter.ofPattern("dd/MM", Locale.ITALIAN))

                when((list[position] as SpecificDayWeather).weather){
                    Weather.SUNNY -> (holder as HomeCardViewHolder).weather.setImageResource(R.drawable.sunny_icon)
                    Weather.CLOUDY -> (holder as HomeCardViewHolder).weather.setImageResource(R.drawable.sun_cloud_icon)
                    Weather.RAINY -> (holder as HomeCardViewHolder).weather.setImageResource(R.drawable.sun_behind_rain_cloud_icon)
                }

                (holder as HomeCardViewHolder).minDegree.text =
                    "${(list[position] as SpecificDayWeather).minDegree}°"

                (holder as HomeCardViewHolder).maxDegree.text =
                    "${(list[position] as SpecificDayWeather).maxDegree}°"

                (holder as HomeCardViewHolder).rainPerc.text =
                    "${(list[position] as SpecificDayWeather).rainPerc}%"

                (holder as HomeCardViewHolder).windKmh.text =
                    "${(list[position] as SpecificDayWeather).windKmh}kmh"

            }

            is NextDays ->
                (holder as HomeSubtitleViewHolder).nextFiveDays.text =
                    (list[position] as NextDays).nextFiveDays
        }
    }



    override fun getItemCount(): Int {
        return list.size
    }
}

