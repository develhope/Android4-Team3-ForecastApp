package co.develhope.meteoapp.ui.searchscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import co.develhope.meteoapp.R
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
        searchViewModel.send(SearchEvent.RetrieveListFromPreferences)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObserver()
        setupFilter()
    }

    private fun setupFilter() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                searchViewModel.send(SearchEvent.WritingOnSearchBar(p0?.trimEnd()))
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                searchViewModel.send(SearchEvent.WritingOnSearchBar(p0?.trimEnd()))
                return true
            }
        })
    }

    private fun setObserver() {
        searchViewModel.searchData2.observe(viewLifecycleOwner) {
            when (it) {
                is SearchResults.Results -> setAdapter(it.results)
                is SearchResults.Errors -> {
                    findNavController().navigate(R.id.searchScreen_to_errorFragment)
                }
            }
        }
    }

    private fun setAdapter(list: List<GetCitiesList>) {
        binding.search.layoutManager = LinearLayoutManager(requireContext())
        binding.search.adapter = SearchAdapter(list) {
            searchViewModel.send(SearchEvent.AddCityToPreferences(it))
            findNavController().navigate(R.id.searchScreenToHomeScreen)
        }
    }
}
