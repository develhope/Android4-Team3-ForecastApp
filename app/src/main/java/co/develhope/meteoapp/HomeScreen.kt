package co.develhope.meteoapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import co.develhope.meteoapp.databinding.FragmentHomeScreenBinding

class HomeScreen : Fragment() {
    private lateinit var binding: FragmentHomeScreenBinding
    private val args: HomeScreenArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeScreenBinding.inflate(inflater, container, false)




        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.homeScreenRecyclerView.adapter = HomeScreenAdapter(DataSource.getHomeItems(), args)
        binding.homeScreenRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

}