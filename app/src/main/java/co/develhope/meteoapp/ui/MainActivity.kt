package co.develhope.meteoapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import co.develhope.meteoapp.R
import co.develhope.meteoapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()  //function to hide the support action bar

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        //used findFragmentById instead of binding for this issue with fragmentManager
        //https://issuetracker.google.com/issues/142847973?pli=1

        navController = navHostFragment.navController
        val bottomNavigationView = binding.bottomNavigationToolbar
        setupWithNavController(bottomNavigationView, navController)
        bottomNavigationView.setupWithNavController(navController)
        bottomNavigationView.setOnItemSelectedListener  {
            when (it.itemId) {
                R.id.homeScreen -> {
                    if (navController.popBackStack(R.id.homeScreen,false)){
                        it.onNavDestinationSelected(navController)
                    };
                    true
                }
                else -> {it.onNavDestinationSelected(navController)
                }
            }
        }
    }
}