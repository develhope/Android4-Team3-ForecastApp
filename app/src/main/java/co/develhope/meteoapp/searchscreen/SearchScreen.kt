package co.develhope.meteoapp.searchscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import co.develhope.meteoapp.NavGraphDirections
import co.develhope.meteoapp.R
import co.develhope.meteoapp.SearchAdapter
import co.develhope.meteoapp.databinding.FragmentSearchScreenBinding
import co.develhope.meteoapp.searchscreen.SearchSource.getSearchCitiesList
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class SearchScreen : Fragment() {

    private lateinit var binding: FragmentSearchScreenBinding

    val adapter = SearchAdapter(getSearchCitiesList(), object : Navigable {
        override fun navigate(card: NavGraphDirections.SearchScreenToHomeScreen) {
            // NAVIGATION
        }
    }, object : Filterable {
    }

    )


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchScreenBinding.inflate(inflater, container, false)
        binding.search.adapter = adapter
        binding.search.layoutManager = LinearLayoutManager(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        retryCall()
    }

    fun retryCall() {

        viewLifecycleOwner.lifecycleScope.launch {
            try {

                RetrofitInstance().apiService.getDetails()


            } catch (e: Exception) {

                Snackbar.make(
                    requireActivity().findViewById(R.id.main_view),
                    "Error",
                    Snackbar.LENGTH_LONG
                ).setAction("retry") {
                    retryCall()
                }.show()

            }
        }
    }
}