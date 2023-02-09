package co.develhope.meteoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import co.develhope.meteoapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()  //function to hide the support action bar

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigationToolbar.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home_navigation_button->{
                    //TODO("add navigation path to home fragment")
                    true
                }
                R.id.today_navigation_button->{
                    loadFragment(Today_screen())
                    true
                }
                R.id.tomorrow_navigation_button->{
                    //TODO("add navigation path to tomorrow fragment")
                    true
                }
                R.id.search_navigation_button->{
                    //TODO("add navigation path to search fragment")
                    true
                }
                else->{
                    //TODO("i don't know why but the ide ask me for the else case")
                    true
                }
            }
        }
    }

    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.commit()
    }

}