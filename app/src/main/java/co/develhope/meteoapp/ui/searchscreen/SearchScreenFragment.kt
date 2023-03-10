package co.develhope.meteoapp.ui.searchscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import co.develhope.meteoapp.data.DataSource.getSearchCitiesList
import android.util.Log
import android.widget.SearchView
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import co.develhope.meteoapp.R
import co.develhope.meteoapp.data.DataSource
import co.develhope.meteoapp.data.RetrofitInstance
import co.develhope.meteoapp.databinding.FragmentSearchScreenBinding
import kotlinx.coroutines.launch
class SearchScreenFragment : Fragment() {

    private lateinit var binding: FragmentSearchScreenBinding

    val adapter = SearchAdapter(
        getSearchCitiesList()


    ) {
        DataSource.setSelectedCity(it)
        findNavController().navigate(R.id.searchScreenToHomeScreen)
    }
    private lateinit var searchView: SearchView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {

        binding = FragmentSearchScreenBinding.inflate(inflater, container, false)
        binding.search.layoutManager = LinearLayoutManager(requireContext())
        binding.search.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        retryCall("Palermo")

        //  searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        //      override fun onQueryTextSubmit(query: String?): Boolean {
        //          // on below line we are checking
        //          // if query exist or not.
        //          if () {
        //              // if query exist within list we
        //              // are filtering our list adapter.
        //              adapter.filter().filter(query)
        //          } else {
        //              // if query is not present we are displaying
        //              // a toast message as no  data found..
        //              Toast.makeText(
        //                  requireContext(),
        //                  "Not found ...",
        //                  Toast.LENGTH_LONG
        //              ).show()
        //          }
        //          return false
        //      }

        //      override fun onQueryTextChange(newText: String?): Boolean {
        //          // if query text is change in that case we
        //          // are filtering our adapter with
        //          // new text on below line.
        //          adapter.filter().filter(newText)
        //          return false
        //      }
        //  })
    }


    private fun retryCall(userSearch : String) {
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                RetrofitInstance().getPlaces(userSearch)

            } catch (e: Exception) {

                Log.d("SearchScreen", "${e.message}, ${e.cause}")

             //  Toast.makeText(
             //      requireContext(),
             //      "Error",
             //      Toast.LENGTH_LONG
             //  ).show()

            }
        }
    }
}