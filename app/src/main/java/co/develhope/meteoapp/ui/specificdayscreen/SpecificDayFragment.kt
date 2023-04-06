package co.develhope.meteoapp.ui.specificdayscreen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import co.develhope.meteoapp.R
import co.develhope.meteoapp.data.DataSource
import co.develhope.meteoapp.data.RetrofitInstance
import co.develhope.meteoapp.data.domainmodel.DomainHourlyForecast
import co.develhope.meteoapp.data.domainmodel.Place
import co.develhope.meteoapp.databinding.FragmentSpecificDayBinding
import co.develhope.meteoapp.ui.MainActivity
import co.develhope.meteoapp.ui.specificdayscreen.specificdayadapter.SpecificDayAdapter
import kotlinx.coroutines.launch
import org.threeten.bp.OffsetDateTime


class SpecificDayFragment : Fragment() {
    private lateinit var binding: FragmentSpecificDayBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (requireActivity() as MainActivity).uncheckAllBottomNavigationItems()
        binding = FragmentSpecificDayBinding.inflate(inflater, container, false)
        val place = DataSource.getSelectedCity()
        val specificDay = DataSource.getSelectedDay()
        binding.todayRecycleView.layoutManager = LinearLayoutManager(requireContext())

        if(place != null && specificDay != null){
            getSpecificDayDetailedForecast(place, specificDay)
        } else {
            findNavController().navigate(R.id.specificDayScreenToHomeScreen)
            Log.e("SpecificDayFragment", "error retrieving place instance")
        }
        return binding.root
    }

    private fun getSpecificDayDetailedForecast(place: Place, specificDay: OffsetDateTime) {
        lifecycleScope.launch {
            try {
                val detailedForecast : List<DomainHourlyForecast> = RetrofitInstance().getHourlyWeather(place,
                    specificDay //TODO this line has to be changed cause
                                                                         // u must insert here only a variable
                                                                         // and no a logic to use on the variable
                )
                val screenItems : List<Forecast> = getSpecificDayScreenItems(detailedForecast, place)
                binding.todayRecycleView.adapter = SpecificDayAdapter(screenItems)
            } catch (e: Exception) {
                Log.e("TodayScreen", "error: $e")
                findNavController().navigate(R.id.specificDayScreen_to_errorFragment)
            }
        }
    }

    private fun getSpecificDayScreenItems(detailedForecast: List<DomainHourlyForecast>, place: Place): List<Forecast> {
        val list: MutableList<Forecast> = mutableListOf()   //list to return
        list.add(Forecast.TitleForecast(detailedForecast.first(), place))   //adding the title to the list
        detailedForecast.map {  //adding all the hourly view for the entire day (0 to 23)
            list.add(Forecast.HourlyForecastListItem(it))
        }
        return list
    }
}