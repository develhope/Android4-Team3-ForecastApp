package co.develhope.meteoapp.ui.specificdayscreen

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import co.develhope.meteoapp.R
import co.develhope.meteoapp.data.DataSource
import co.develhope.meteoapp.data.RetrofitInstance
import co.develhope.meteoapp.data.domainmodel.DomainHourlyForecast
import co.develhope.meteoapp.data.domainmodel.Place
import co.develhope.meteoapp.databinding.FragmentSpecificDayBinding
import kotlinx.coroutines.launch
import org.threeten.bp.DayOfWeek
import org.threeten.bp.OffsetDateTime


class SpecificDayFragment : Fragment() {
    private lateinit var binding: FragmentSpecificDayBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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

    private fun getSpecificDayDetailedForecast(place: Place, specificDay: DayOfWeek) {
        lifecycleScope.launch {
            try {
                val detailedForecast : List<DomainHourlyForecast> = RetrofitInstance().getHourlyWeather(place, OffsetDateTime.now().with(specificDay).toLocalDate())
                val screenItems : List<Forecast> = getSpecificDayScreenItems(detailedForecast, place)
                binding.todayRecycleView.adapter = SpecificDayAdapter(screenItems)
            } catch (e: Exception) {
                Log.e("TodayScreen", "error: $e")
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