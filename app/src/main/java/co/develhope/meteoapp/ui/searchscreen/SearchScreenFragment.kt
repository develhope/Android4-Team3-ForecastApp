package co.develhope.meteoapp.ui.searchscreen

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import co.develhope.meteoapp.data.DataSource.getSearchCitiesList
import android.util.Log
import android.widget.EditText
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import co.develhope.meteoapp.R
import co.develhope.meteoapp.data.DataSource
import co.develhope.meteoapp.data.RetrofitInstance
import co.develhope.meteoapp.data.domainmodel.Place
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
    private lateinit var searchView: EditText


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {
      //searchView.findViewById<EditText>(R.id.edit_query)
        binding = FragmentSearchScreenBinding.inflate(inflater, container, false)
        binding.search.layoutManager = LinearLayoutManager(requireContext())
        binding.search.adapter = adapter
      // searchView.addTextChangedListener(object : TextWatcher{
      //     override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
      //         TODO("Not yet implemented")
      //     }

      //     override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
      //         TODO("Not yet implemented")
      //     }

      //     override fun afterTextChanged(p0: Editable?) {
      //         TODO("Not yet implemented")
      //     }
      // })
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        retryCall("Palermo")

    }


    private fun retryCall(userSearch : String) {
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                RetrofitInstance().getPlaces(userSearch)

            } catch (e: Exception) {

               // Log.d("SearchScreen", "${e.message}, ${e.cause}")

               Toast.makeText(
                   requireContext(),
                   "Error",
                   Toast.LENGTH_LONG
               ).show()

            }
        }
    }
}