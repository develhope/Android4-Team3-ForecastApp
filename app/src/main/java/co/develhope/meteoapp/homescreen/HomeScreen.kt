package co.develhope.meteoapp.homescreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import co.develhope.meteoapp.data.HomePageItems
import co.develhope.meteoapp.HomeScreenAdapter
import co.develhope.meteoapp.OnCardClick
import co.develhope.meteoapp.R
import co.develhope.meteoapp.data.DataSource
import co.develhope.meteoapp.databinding.FragmentHomeScreenBinding
import org.threeten.bp.OffsetDateTime

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

        val adapter = HomeScreenAdapter(DataSource.getHomeItems(), object: OnCardClick{
            override fun onCardClick(card: HomePageItems.SpecificDayWeather) {
                when(card.date.dayOfWeek){
                    OffsetDateTime.now().dayOfWeek ->  findNavController().
                    navigate(R.id.homeScreen_to_todayScreen)
                    else ->  findNavController().navigate(R.id.homeScreen_to_specificDayScreen)
                }
            }
        }, args)
        binding.homeScreenRecyclerView.adapter = adapter
        binding.homeScreenRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

}






