package co.develhope.meteoapp

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import co.develhope.meteoapp.SearchSource.getSearchCitiesList
import co.develhope.meteoapp.databinding.FragmentSearchScreenBinding

class SearchScreen : Fragment() {

    private lateinit var binding: FragmentSearchScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchScreenBinding.inflate(inflater, container, false)
        binding.search.adapter = SearchAdapter(getSearchCitiesList())
        binding.search.layoutManager = LinearLayoutManager(requireContext())
        return binding.root
    }

}