package co.develhope.meteoapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import co.develhope.meteoapp.DataSource.getHourlyForecast
import co.develhope.meteoapp.databinding.FragmentTodayScreenBinding


class TodayScreen : Fragment() {
    lateinit var binding: FragmentTodayScreenBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTodayScreenBinding.inflate(inflater, container, false)
        binding.todayRecycleView.adapter = Adapter(getHourlyForecast())
        binding.todayRecycleView.layoutManager = LinearLayoutManager(requireContext())
        return binding.root
    }

}