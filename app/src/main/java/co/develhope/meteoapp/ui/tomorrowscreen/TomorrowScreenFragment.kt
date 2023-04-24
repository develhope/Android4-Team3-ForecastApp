package co.develhope.meteoapp.ui.tomorrowscreen

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
import co.develhope.meteoapp.databinding.FragmentTomorrowScreenBinding
import co.develhope.meteoapp.ui.errorscreen.ErrorFragment
import co.develhope.meteoapp.ui.tomorrowscreen.tomorrowadapter.TomorrowAdapter

class TomorrowScreenFragment : Fragment() {
    private lateinit var binding: FragmentTomorrowScreenBinding
    private val tomorrowAdapter: TomorrowAdapter by lazy { TomorrowAdapter() }
    private lateinit var viewModel: TomorrowScreenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[TomorrowScreenViewModel::class.java]
        viewModel.getPrefAndSelectedDayOrNavigate { findNavController().navigate(R.id.tomorrowScreen_to_searchScreen) }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTomorrowScreenBinding.inflate(inflater, container, false)
        binding.specificDayRecycleView.layoutManager = LinearLayoutManager(requireContext())
        binding.specificDayRecycleView.adapter = tomorrowAdapter
        observeTomorrowList()
        return binding.root
    }

    private fun observeTomorrowList() {
        viewModel.forecastList.observe(viewLifecycleOwner){
            when(it){
                is TomorrowVMResults.Result -> tomorrowAdapter.submitList(it.result)
                is TomorrowVMResults.Error -> {
                    Log.e("TodayScreen", "error: ${it.error}")
                    ErrorFragment.show(childFragmentManager){
                        viewModel.getPrefAndSelectedDayOrNavigate {
                            findNavController().navigate(R.id.tomorrowScreen_to_searchScreen)
                        }
                    }
                }
            }
        }
    }
}