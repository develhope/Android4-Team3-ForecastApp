package co.develhope.meteoapp.todayscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import co.develhope.meteoapp.databinding.FragmentTodayScreenBinding
import co.develhope.meteoapp.todayscreen.DataSourceTodayScreen.getTodayDetailedForecast


class TodayScreen : Fragment() {
    lateinit var binding: FragmentTodayScreenBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTodayScreenBinding.inflate(inflater, container, false)
        binding.todayRecycleView.adapter = Adapter(getTodayDetailedForecast())
        binding.todayRecycleView.layoutManager = LinearLayoutManager(requireContext())
        return binding.root
    }

}