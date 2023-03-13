package co.develhope.meteoapp.ui.todayscreen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import co.develhope.meteoapp.data.DataSource
import co.develhope.meteoapp.data.DataSource.getTodayDetailedForecast
import co.develhope.meteoapp.data.RetrofitInstance
import co.develhope.meteoapp.data.domainmodel.DomainHourlyForecast
import co.develhope.meteoapp.data.domainmodel.Place
import co.develhope.meteoapp.databinding.FragmentTodayScreenBinding
import kotlinx.coroutines.launch


class TodayScreenFragment : Fragment() {
    private lateinit var binding: FragmentTodayScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTodayScreenBinding.inflate(inflater, container, false)
        binding.todayRecycleView.adapter = TodayAdapter(getTodayDetailedForecast()) //TODO change this
        binding.todayRecycleView.layoutManager = LinearLayoutManager(requireContext())

        val place = DataSource.getSelectedCity()
        if(place != null){
            getDetailedForecast(place)
        }else{
            //navigate to search
        }

        return binding.root
    }

    private fun getDetailedForecast(place: Place) {
        lifecycleScope.launch {
            try {
                val detailedForecast : List<DomainHourlyForecast> = RetrofitInstance().getHourlyWeather(place)


                val screenItems : List<Forecast> = getScreenItems(detailedForecast)
                binding.todayRecycleView.adapter = TodayAdapter(screenItems)
            } catch (e: Exception) {
                Log.e("TodayScreen", "error: $e")
            }
        }
    }

    private fun getScreenItems(detailedForecast: List<DomainHourlyForecast>): List<Forecast> {
        return emptyList()
    }

}