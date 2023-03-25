package co.develhope.meteoapp.ui.homescreen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import co.develhope.meteoapp.HomeScreenAdapter
import co.develhope.meteoapp.OnCardClick
import co.develhope.meteoapp.R
import co.develhope.meteoapp.data.DataSource
import co.develhope.meteoapp.data.RetrofitInstance
import co.develhope.meteoapp.data.domainmodel.Place
import co.develhope.meteoapp.databinding.FragmentHomeScreenBinding
import co.develhope.meteoapp.ui.MainActivity
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
           findNavController().navigate(R.id.homeScreen_to_searchScreen)
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

            val response = RetrofitInstance().retrieveDailyDetails(place.latitude, place.longitude).daily.toDomain()
            val listScreen = listOf(
                HomePageItems.HomeTitle(Title(place.name, place.region)),
                HomePageItems.SpecificDayWeather(response[0]),
                HomePageItems.NextDays(R.string.next_5_days.toString()),
                HomePageItems.SpecificDayWeather(response[1]),
                HomePageItems.SpecificDayWeather(response[2]),
                HomePageItems.SpecificDayWeather(response[3]),
                HomePageItems.SpecificDayWeather(response[4]),
                HomePageItems.SpecificDayWeather(response[5])
            )
            val adapter = HomeScreenAdapter(listScreen, object : OnCardClick {
                override fun onCardClick(card: HomePageItems.SpecificDayWeather) {
                    when (card.homeCardWeather.date.dayOfWeek) {
                        OffsetDateTime.now().dayOfWeek -> findNavController().navigate(R.id.homeScreen_to_todayScreen)
                        OffsetDateTime.now().plusDays(1).dayOfWeek -> findNavController().navigate(R.id.homeScreen_to_tomorrowScreen)
                        in OffsetDateTime.now().plusDays(2).dayOfWeek..OffsetDateTime.now().plusDays(7).dayOfWeek -> {
                            (requireActivity() as MainActivity).uncheckAllBottomNavigationItems()
                            DataSource.setSelectedDay(card.homeCardWeather.date.dayOfWeek)
                            findNavController().navigate(R.id.homeScreen_to_specificDayScreen)
                        }
                        else -> Log.e("HomeScreenFragment", "error navigating from home cards to today, tomorrow and specific day fragment")
                    }
                }
            })
            binding.homeScreenRecyclerView.adapter = adapter
        }
    }
}






