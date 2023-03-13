package co.develhope.meteoapp.ui.specificdayscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import co.develhope.meteoapp.databinding.FragmentSpecificDayScreenBinding
import co.develhope.meteoapp.ui.todayscreen.TodayAdapter
import co.develhope.meteoapp.data.DataSource.getTodayDetailedForecast


class SpecificDayScreen : Fragment() {
    private lateinit var binding: FragmentSpecificDayScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSpecificDayScreenBinding.inflate(inflater, container, false)
        binding.specificDayRecycleView.adapter = TodayAdapter(getTodayDetailedForecast())
        binding.specificDayRecycleView.layoutManager = LinearLayoutManager(requireContext())
        return binding.root
    }


}