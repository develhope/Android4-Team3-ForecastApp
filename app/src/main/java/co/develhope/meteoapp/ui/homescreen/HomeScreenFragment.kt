package co.develhope.meteoapp.ui.homescreen

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import co.develhope.meteoapp.R
import co.develhope.meteoapp.data.DataSource
import co.develhope.meteoapp.data.domainmodel.Place
import co.develhope.meteoapp.data.dto.HomeCardWeather
import co.develhope.meteoapp.data.dto.Title
import co.develhope.meteoapp.databinding.FragmentHomeScreenBinding
import org.threeten.bp.OffsetDateTime

class HomeScreenFragment : Fragment() {
    private lateinit var binding: FragmentHomeScreenBinding
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeScreenBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val place = DataSource.getSelectedCity()

        if(place != null){
            observeViewModel(place)
            viewModel.retrieveForecast(place)
        }else{
           findNavController().navigate(R.id.homeScreen_to_searchScreen)
        }

        binding.homeScreenRecyclerView.layoutManager = LinearLayoutManager(requireContext())

    }

    private fun observeViewModel(place: Place){
        viewModel.dailyForecast.observe(viewLifecycleOwner) {
            when(it){
                is HomeViewModel.NetworkCall.Success -> setHomeUi(it.dailyInfo, place)
                is HomeViewModel.NetworkCall.Error -> Log.d("Error", "Error")
            }
        }
    }

    private fun setHomeUi(homeCardList: List<HomeCardWeather>, place: Place){
        val list = getHomeItems(homeCardList, place)

        val adapter = HomeScreenAdapter(list, object : OnCardClick {
            override fun onCardClick(card: HomePageItems.SpecificDayWeather) {
                DataSource.setSelectedDay(card.homeCardWeather.date)
                when (card.homeCardWeather.date.dayOfWeek) {
                    OffsetDateTime.now().dayOfWeek -> findNavController().navigate(R.id.homeScreen_to_todayScreen)
                    OffsetDateTime.now().plusDays(1).dayOfWeek -> findNavController().navigate(R.id.homeScreen_to_tomorrowScreen)
                    else -> { findNavController().navigate(R.id.homeScreen_to_specificDayScreen) }
                }
            }
        })
        binding.homeScreenRecyclerView.adapter = adapter
    }

    private fun getHomeItems(weeklyCards: List<HomeCardWeather>, place: Place): List<HomePageItems>{
        val list = ArrayList<HomePageItems>()
      list.add(
          HomePageItems.HomeTitle(Title(place.name, place.region))
      )
      list.add(
          HomePageItems.SpecificDayWeather(weeklyCards.first())
      )
      list.add(
          HomePageItems.NextDays(R.string.next_5_days.toString())
      )
      val restOfList = weeklyCards.drop(1)
      restOfList.map {
          list.add(HomePageItems.SpecificDayWeather(it))
      }
      return list
    }
}






