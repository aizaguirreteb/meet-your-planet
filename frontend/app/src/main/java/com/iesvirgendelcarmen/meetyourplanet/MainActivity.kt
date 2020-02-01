package com.iesvirgendelcarmen.meetyourplanet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.fragment.app.Fragment

import com.iesvirgendelcarmen.meetyourplanet.fragment.HomeFragment
import com.iesvirgendelcarmen.meetyourplanet.fragment.SystemsListFragment

class MainActivity : AppCompatActivity() {

    private val listFragment: SystemsListFragment = SystemsListFragment()
    private val homeFragment: HomeFragment = HomeFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)



        if(savedInstanceState == null){
            supportFragmentManager
                .beginTransaction()
                .add(R.id.container, homeFragment)
                .commit()
        }


    }


    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack(null)
            .commit()
    }

}
