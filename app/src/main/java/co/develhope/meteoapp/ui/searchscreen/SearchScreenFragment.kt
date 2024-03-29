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
import co.develhope.meteoapp.ui.errorscreen.ErrorFragment


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
        setObserver("")
        searchViewModel.send(SearchEvent.RetrieveListFromPreferences)
        setupFilter()
    }

    private fun setupFilter() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                searchViewModel.send(SearchEvent.WritingOnSearchBar(p0?.trimEnd()))
                setObserver(p0)
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                searchViewModel.send(SearchEvent.WritingOnSearchBar(p0?.trimEnd()))
                setObserver(p0)
                return true
            }
        })
    }

    private fun setObserver(check: String?) {
        searchViewModel.searchData2.observe(viewLifecycleOwner) {
            when (it) {
                is SearchResults.Results -> setAdapter(it.results, check)
                is SearchResults.Errors -> {
                    ErrorFragment.show(childFragmentManager){searchViewModel.send(SearchEvent.WritingOnSearchBar(check))}
                }
            }
        }
    }

    private fun setAdapter(list: List<GetCitiesList>, check : String?) {
        binding.search.layoutManager = LinearLayoutManager(requireContext())
        if (check != null) {
            binding.search.adapter = SearchAdapter(if(check.length >1){
                list.filter { it != GetCitiesList.RecentSearches }
            }else if(check.isEmpty()){
                list
            }else{
                emptyList()
            }) {
                searchViewModel.send(SearchEvent.AddCityToPreferences(it))
                findNavController().navigate(R.id.searchScreenToHomeScreen)
            }
        }
    }
}