package com.iesvirgendelcarmen.meetyourplanet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.iesvirgendelcarmen.meetyourplanet.adapters.SystemRecyclerAdapter
import com.iesvirgendelcarmen.meetyourplanet.model.PlanetarySystem
import com.iesvirgendelcarmen.meetyourplanet.model.Resource
import com.iesvirgendelcarmen.meetyourplanet.model.SystemViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var systemListRecycler : RecyclerView
    private lateinit var systemAdapter: SystemRecyclerAdapter

    private val systemViewModel: SystemViewModel by lazy {
        ViewModelProviders.of(this).get(SystemViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var clickListener = object: SystemRecyclerAdapter.OnItemClickListener{

            override fun onClicked(planetarySystem: PlanetarySystem) {
                Toast.makeText(baseContext,
                    "${planetarySystem.star} + ${planetarySystem.id}",
                    Toast.LENGTH_SHORT).show()
            }
        }

        systemAdapter = SystemRecyclerAdapter(emptyList(), clickListener)

        systemListRecycler = findViewById<RecyclerView>(R.id.systemsRecyclerList).apply{
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = systemAdapter
        }
    }

    override fun onStart() {
        super.onStart()
        observeSystemList()
        systemViewModel.getAllPlanetarySystems()
    }

    private fun observeSystemList(){
        systemViewModel.systemListLiveData.observe(this, Observer {
            resource ->
                when(resource.status){
                    Resource.Status.SUCCESS -> {
                        systemAdapter.systems = resource.data
                        systemAdapter.notifyDataSetChanged()
                    }
                    Resource.Status.ERROR -> {
                        if(resource.message != null ){
                            Toast.makeText(baseContext, resource.message , Toast.LENGTH_LONG).show()
                        }
                    }
                    Resource.Status.LOADING -> {
                        Toast.makeText(baseContext, "Loading Systems" , Toast.LENGTH_LONG).show()

                    }
                }
        })
    }
}
