package co.develhope.meteoapp.ui.homescreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import co.develhope.meteoapp.HomeScreenAdapter
import co.develhope.meteoapp.OnCardClick
import co.develhope.meteoapp.R
import co.develhope.meteoapp.data.DataSource
import co.develhope.meteoapp.data.RetrofitInstance
import co.develhope.meteoapp.data.domainmodel.Place
import co.develhope.meteoapp.databinding.FragmentHomeScreenBinding
import kotlinx.coroutines.launch
import org.threeten.bp.OffsetDateTime

class HomeScreenFragment : Fragment() {
    private lateinit var binding: FragmentHomeScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeScreenBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val place = DataSource.getSelectedCity()

        if(place != null){
            getDailyForecast(place)
        }else{
           //navigate to search
        }

      // val adapter = HomeScreenAdapter(DataSource.getHomeItems(), object: OnCardClick{
      //     override fun onCardClick(card: HomePageItems.SpecificDayWeather) {
      //         when(card.homeCardWeather.date.dayOfWeek){
      //             OffsetDateTime.now().dayOfWeek ->  findNavController().
      //             navigate(R.id.homeScreen_to_todayScreen)
      //             else ->  findNavController().navigate(R.id.homeScreen_to_specificDayScreen)
      //         }
      //     }
      // }, args)
      //  binding.homeScreenRecyclerView.adapter = adapter
        binding.homeScreenRecyclerView.layoutManager = LinearLayoutManager(requireContext())

    }

    private fun getDailyForecast(place: Place) {
        viewLifecycleOwner.lifecycleScope.launch {

            val response = RetrofitInstance().retrieveDailyDetails(place.latitude, place.longitude)?.daily?.toDomain()
            val listScreen = listOf(
                HomePageItems.HomeTitle(Title(place.name, "Sicilia")),
                HomePageItems.SpecificDayWeather(response?.getOrNull(0) ?: DataSource.cardView),
                HomePageItems.NextDays(R.string.next_5_days.toString()),
                HomePageItems.SpecificDayWeather(response?.getOrNull(1) ?: DataSource.cardView),
                HomePageItems.SpecificDayWeather(response?.getOrNull(2) ?: DataSource.cardView),
                HomePageItems.SpecificDayWeather(response?.getOrNull(3) ?: DataSource.cardView),
                HomePageItems.SpecificDayWeather(response?.getOrNull(4) ?: DataSource.cardView),
                HomePageItems.SpecificDayWeather(response?.getOrNull(5) ?: DataSource.cardView)
            )
            val adapter = HomeScreenAdapter(listScreen, object : OnCardClick {
                override fun onCardClick(card: HomePageItems.SpecificDayWeather) {
                    when (card.homeCardWeather.date.dayOfWeek) {
                        OffsetDateTime.now().dayOfWeek -> findNavController().navigate(R.id.homeScreen_to_todayScreen)
                        else -> findNavController().navigate(R.id.homeScreen_to_specificDayScreen)
                    }
                }
            })
            binding.homeScreenRecyclerView.adapter = adapter
        }
    }
}






