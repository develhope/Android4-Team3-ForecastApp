package co.develhope.meteoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import co.develhope.meteoapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigationToolbar.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home_navigation_button->{
                    //TODO("add navigation path to home fragment")
                    true
                }
                R.id.today_navigation_button->{
                    //TODO("add navigation path to today fragment")
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
}