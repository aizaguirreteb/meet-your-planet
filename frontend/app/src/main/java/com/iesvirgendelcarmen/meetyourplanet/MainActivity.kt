package com.iesvirgendelcarmen.meetyourplanet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.iesvirgendelcarmen.meetyourplanet.adapters.SystemRecyclerAdapter
import com.iesvirgendelcarmen.meetyourplanet.fragment.HomeFragment
import com.iesvirgendelcarmen.meetyourplanet.fragment.SystemsListFragment
import com.iesvirgendelcarmen.meetyourplanet.model.PlanetarySystem
import com.iesvirgendelcarmen.meetyourplanet.model.Resource
import com.iesvirgendelcarmen.meetyourplanet.model.SystemViewModel

class MainActivity : AppCompatActivity() {

    private val listFragment: SystemsListFragment = SystemsListFragment()
    private val homeFragment: HomeFragment = HomeFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
