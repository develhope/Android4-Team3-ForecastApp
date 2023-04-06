package co.develhope.meteoapp.ui.searchscreen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import co.develhope.meteoapp.R
import co.develhope.meteoapp.data.DataSource
import co.develhope.meteoapp.data.DataSource.getRecentSearches
import co.develhope.meteoapp.data.domainmodel.Place
import co.develhope.meteoapp.databinding.FragmentSearchScreenBinding

class SearchScreenFragment : Fragment() {

    private lateinit var searchViewModel: SearchViewModel
    private lateinit var binding: FragmentSearchScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {
        binding = FragmentSearchScreenBinding.inflate(inflater, container, false)
        searchViewModel = ViewModelProvider(this)[SearchViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter(listOf())
        setupFilter()
    }

    private fun setupFilter() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                searchViewModel.searchApi(p0)
                retryCall()
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                // Gestire la cancellazione del testo per eliminare la chiamata di rete
                // Gestire i suggerimenti
                searchViewModel.searchApi(p0)
                retryCall()
                return true
            }
        })
    }

    private fun setAdapter(list: List<Place>): SearchAdapter {
        val listAdapter = mutableListOf(getRecentSearches())
        listAdapter.addAll(
            1,
            list.map {
                GetCitiesList.Cities(it)
            }
        )
        val adapter = SearchAdapter(listAdapter) {
            DataSource.setSelectedCity(it)
            findNavController().navigate(R.id.searchScreenToHomeScreen)
        }
        binding.search.adapter = adapter
        binding.search.layoutManager = LinearLayoutManager(requireContext())
        return adapter
    }

    private fun retryCall() {
        searchViewModel.searchData2.observe(viewLifecycleOwner) {
            when (it) {
                is SearchResults.Results -> setAdapter(it.results)
                is SearchResults.Errors -> {
                    findNavController().navigate(R.id.searchScreen_to_errorFragment)
                }
            }
        }
    }
}