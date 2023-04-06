package co.develhope.meteoapp.ui.todayscreen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import co.develhope.meteoapp.R
import co.develhope.meteoapp.data.DataSource
import co.develhope.meteoapp.databinding.FragmentTodayScreenBinding
import co.develhope.meteoapp.ui.todayscreen.todayadapter.TodayAdapter

class TodayScreenFragment : Fragment() {
    private lateinit var binding: FragmentTodayScreenBinding
    private lateinit var viewModel: TodayScreenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[TodayScreenViewModel::class.java]

        val place = DataSource.getSelectedCity()
        val specificDay = DataSource.getSelectedDay()
        if(place != null && specificDay != null){
            viewModel.getDetailedForecast(place, specificDay)
        }else{
            findNavController().navigate(R.id.todayScreen_to_errorFragment)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTodayScreenBinding.inflate(inflater, container, false)
        binding.todayRecycleView.layoutManager = LinearLayoutManager(requireContext())
        observeDetailedForecastList()
        return binding.root
    }

    private fun observeDetailedForecastList(){
        viewModel.forecastList.observe(viewLifecycleOwner) {
            binding.todayRecycleView.adapter = TodayAdapter(it)
        }
        viewModel.error.observe(viewLifecycleOwner) {
            Log.e("TodayScreenFragment", it) //TODO replace this with error fragment navigation
        }
    }
}