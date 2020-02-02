package com.iesvirgendelcarmen.meetyourplanet

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener
import com.iesvirgendelcarmen.meetyourplanet.fragment.HomeFragment
import com.iesvirgendelcarmen.meetyourplanet.fragment.SystemsListFragment


class MainActivity : AppCompatActivity(), OnNavigationItemSelectedListener{

    private val listFragment: SystemsListFragment = SystemsListFragment()
    private val homeFragment: HomeFragment = HomeFragment()
    lateinit var drawerLayout: DrawerLayout
    lateinit var navigationView: NavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        var abar = supportActionBar
        if(abar != null){
            abar.setHomeAsUpIndicator(R.drawable.menu)
            abar.setDisplayHomeAsUpEnabled(true)

            abar.setTitle("MEET YOUR PLANET")
        }

        drawerLayout = findViewById(R.id.drawer)
        navigationView = findViewById(R.id.navigator)


        navigationView.setNavigationItemSelectedListener(this)
        if(savedInstanceState == null) {
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

    private fun configurarDrawer(navigationView: NavigationView) {


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                drawerLayout.openDrawer(GravityCompat.START)
            }
        }
        return true
    }



    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.nav_logino -> {
                Toast.makeText(this, "Login", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_systems -> {
                changeFragment(listFragment)
            }
            R.id.nav_home -> {
                changeFragment(homeFragment)
            }

        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}
