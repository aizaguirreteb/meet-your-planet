package com.iesvirgendelcarmen.meetyourplanet

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener
import com.iesvirgendelcarmen.meetyourplanet.config.ApiConfig
import com.iesvirgendelcarmen.meetyourplanet.fragment.HomeFragment
import com.iesvirgendelcarmen.meetyourplanet.fragment.LoginFragment
import com.iesvirgendelcarmen.meetyourplanet.fragment.SystemsListFragment


class MainActivity : AppCompatActivity(), OnNavigationItemSelectedListener, LoginFragment.LoginDone{

    private val listFragment: SystemsListFragment = SystemsListFragment()
    private val homeFragment: HomeFragment = HomeFragment()
    private val loginFragment: LoginFragment = LoginFragment()
    lateinit var drawerLayout: DrawerLayout
    lateinit var navigationView: NavigationView
    lateinit var prefs: SharedPreferences;
    lateinit var prefsEditor: SharedPreferences.Editor;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val abar = supportActionBar
        if(abar != null){
            abar.setHomeAsUpIndicator(R.drawable.menu)
            abar.setDisplayHomeAsUpEnabled(true)

            abar.title = "MEET YOUR PLANET"
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

        checkSavedLogin()
    }

    private fun checkSavedLogin() {
        prefs = getSharedPreferences("login", Context.MODE_PRIVATE)
        ApiConfig.token = prefs.getString("token", "").orEmpty()
        if (ApiConfig.token != "")
            onLoginDone(true)
    }

    fun changeFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack(null)
            .commit()
    }

    fun changeFragmentNotBackStack(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
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
            R.id.nav_login -> {
                changeFragmentNotBackStack(loginFragment)
            }
            R.id.nav_systems -> {
                changeFragment(listFragment)
            }
            R.id.nav_home -> {
                changeFragmentNotBackStack(homeFragment)
            }
            R.id.nav_logout -> {
                onLoginDone(false)
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

    override fun onLoginDone(status: Boolean) {
        navigationView.menu.getItem(1).isEnabled = status
        navigationView.menu.getItem(2).isEnabled = !status
        navigationView.menu.getItem(3).isEnabled = status
        if (status) {
            changeFragmentNotBackStack(homeFragment)
            prefsEditor = prefs.edit()
            prefsEditor.putString("token", ApiConfig.token)
            prefsEditor.commit()
        } else {
            changeFragment(homeFragment)
            prefsEditor = prefs.edit()
            prefsEditor.putString("token", "")
            prefsEditor.commit()
        }
    }


}
