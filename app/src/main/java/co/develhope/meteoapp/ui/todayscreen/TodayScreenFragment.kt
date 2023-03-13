package co.develhope.meteoapp.ui.todayscreen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import co.develhope.meteoapp.data.DataSource.getTodayDetailedForecast
import co.develhope.meteoapp.databinding.FragmentTodayScreenBinding
import co.develhope.meteoapp.data.HourlyForecastListItem
import co.develhope.meteoapp.data.RetrofitInstanceeee
import co.develhope.meteoapp.data.HourlyWeatherService
import kotlinx.coroutines.launch


class TodayScreenFragment : Fragment() {
    private lateinit var binding: FragmentTodayScreenBinding

    private val api: HourlyWeatherService by lazy {
        RetrofitInstanceeee().getClient().create(HourlyWeatherService::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTodayScreenBinding.inflate(inflater, container, false)
        binding.todayRecycleView.adapter = TodayAdapter(getTodayDetailedForecast()) //TODO change this
        binding.todayRecycleView.layoutManager = LinearLayoutManager(requireContext())

        lifecycleScope.launch {
            try {
                binding.todayRecycleView.adapter = TodayAdapter(
                    getTodayDetailedForecast().plus(
                        //TODO replace this when u know how to use a view model
                        api.getDtoHourlyWeather(40.8532f, 14.3055f)
                            .toDomainHourlyForecast().map {
                                HourlyForecastListItem(it)
                            }
                    )
                )
            } catch (e: Exception) {
                Log.e("TodayScreen", "error: $e")
            }
        }

        return binding.root
    }

}