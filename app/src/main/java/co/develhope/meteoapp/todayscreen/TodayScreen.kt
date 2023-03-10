package co.develhope.meteoapp.todayscreen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import co.develhope.meteoapp.databinding.FragmentTodayScreenBinding
import co.develhope.meteoapp.todayscreen.DataSourceTodayScreen.getTodayDetailedForecast
import co.develhope.meteoapp.todayscreen.api.RetrofitInstance
import co.develhope.meteoapp.todayscreen.api.HourlyWeatherService
import co.develhope.meteoapp.todayscreen.domain.ToTodayScreenMapper.toDomainHourlyForecast
import kotlinx.coroutines.launch


class TodayScreen : Fragment() {
    private lateinit var binding: FragmentTodayScreenBinding

    private val api: HourlyWeatherService by lazy {
        RetrofitInstance().getClient().create(HourlyWeatherService::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTodayScreenBinding.inflate(inflater, container, false)
        binding.todayRecycleView.adapter = Adapter(getTodayDetailedForecast()) //TODO change this
        binding.todayRecycleView.layoutManager = LinearLayoutManager(requireContext())

        lifecycleScope.launch {
            try {
                binding.todayRecycleView.adapter = Adapter(
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