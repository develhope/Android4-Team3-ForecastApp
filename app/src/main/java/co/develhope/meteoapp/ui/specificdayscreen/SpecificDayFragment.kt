package co.develhope.meteoapp.ui.specificdayscreen

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
import co.develhope.meteoapp.databinding.FragmentSpecificDayBinding
import co.develhope.meteoapp.ui.MainActivity
import co.develhope.meteoapp.ui.errorscreen.ErrorFragment
import co.develhope.meteoapp.ui.specificdayscreen.specificdayadapter.SpecificDayAdapter

class SpecificDayFragment : Fragment() {
    private lateinit var binding: FragmentSpecificDayBinding
    private lateinit var viewModel: SpecificDayViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[SpecificDayViewModel::class.java]
        viewModel.getPrefAndSelectedDayOrNavigate { findNavController().navigate(R.id.specificDayScreenToHomeScreen) }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (requireActivity() as MainActivity).uncheckAllBottomNavigationItems()
        binding = FragmentSpecificDayBinding.inflate(inflater, container, false)
        binding.todayRecycleView.layoutManager = LinearLayoutManager(requireContext())
        observeList()
        return binding.root
    }

    private fun observeList() {
        viewModel.forecastList.observe(viewLifecycleOwner){
            when(it){
                is SpecificDayVMResults.Result -> binding.todayRecycleView.adapter = SpecificDayAdapter(it.result)
                is SpecificDayVMResults.Error -> {
                    Log.e("TodayScreenFragment", it.error)
                    ErrorFragment.show(childFragmentManager) {
                        viewModel.getPrefAndSelectedDayOrNavigate {
                            findNavController().navigate(R.id.specificDayScreenToHomeScreen)
                        }
                    }
                }
            }
        }
    }
}