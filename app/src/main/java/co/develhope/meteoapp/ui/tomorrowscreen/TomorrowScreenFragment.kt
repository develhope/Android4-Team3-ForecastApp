package co.develhope.meteoapp.ui.tomorrowscreen

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
import co.develhope.meteoapp.databinding.FragmentTomorrowScreenBinding
import co.develhope.meteoapp.ui.tomorrowscreen.tomorrowadapter.TomorrowAdapter
import kotlinx.coroutines.launch
import org.threeten.bp.OffsetDateTime

class TomorrowScreenFragment : Fragment() {
    private lateinit var binding: FragmentTomorrowScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTomorrowScreenBinding.inflate(inflater, container, false)
        val place = DataSource.getSelectedCity()
        val specificDay = DataSource.getSelectedDay()
        binding.specificDayRecycleView.layoutManager = LinearLayoutManager(requireContext())
        if(place != null && specificDay != null){
            getSpecificDayDetailedForecast(place, specificDay)
        }else{
            findNavController().navigate(R.id.tomorrowScreen_to_searchScreen)
        }
        return binding.root
    }

    private fun getSpecificDayDetailedForecast(place: Place, specificDay: OffsetDateTime) {
        lifecycleScope.launch {
            try {
                val detailedForecast : List<DomainHourlyForecast> = RetrofitInstance().getHourlyWeather(place, specificDay)
                val screenItems : List<Forecast> = getSpecificDayScreenItems(detailedForecast, place)
                binding.specificDayRecycleView.adapter = TomorrowAdapter(screenItems)
            } catch (e: Exception) {
                Log.e("TodayScreen", "error: $e")
                findNavController().navigate(R.id.tomorrowScreen_to_errorFragment)
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