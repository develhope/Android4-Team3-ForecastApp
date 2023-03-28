package co.develhope.meteoapp.ui.todayscreen

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
import co.develhope.meteoapp.databinding.FragmentTodayScreenBinding
import kotlinx.coroutines.launch
import org.threeten.bp.OffsetDateTime

class TodayScreenFragment : Fragment() {
    private lateinit var binding: FragmentTodayScreenBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTodayScreenBinding.inflate(inflater, container, false)
        binding.todayRecycleView.layoutManager = LinearLayoutManager(requireContext())

        val place = DataSource.getSelectedCity()
        val specificDay = DataSource.getSelectedDay()
        if(place != null && specificDay != null){
            getTodayDetailedForecast(place, specificDay)
        }else{
            findNavController().navigate(R.id.todayScreen_to_searchScreen)
        }
        return binding.root
    }

    private fun getTodayDetailedForecast(place: Place, specificDay: OffsetDateTime) {
        lifecycleScope.launch {
            try {
                val detailedForecast : List<DomainHourlyForecast> = RetrofitInstance().getHourlyWeather(place, specificDay)
                val screenItems : List<Forecast> = getTodayScreenItems(detailedForecast, place)
                binding.todayRecycleView.adapter = TodayAdapter(screenItems)
            } catch (e: Exception) {
                Log.e("TodayScreen", "error: $e")
            }
        }
    }

    private fun getTodayScreenItems(detailedForecast: List<DomainHourlyForecast>, place: Place): List<Forecast> {
        val list: MutableList<Forecast> = mutableListOf()   //list to return
        list.add(Forecast.TitleForecast(detailedForecast.first(), place))   //adding the title to the list
        detailedForecast.map {  //adding all the hourly view for the entire day (0 to 23)
            list.add(Forecast.HourlyForecastListItem(it))
        }
        return list
    }
}