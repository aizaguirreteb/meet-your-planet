package com.iesvirgendelcarmen.meetyourplanet.fragment

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.iesvirgendelcarmen.meetyourplanet.R
import com.iesvirgendelcarmen.meetyourplanet.adapters.PlanetRecyclerAdapter
import com.iesvirgendelcarmen.meetyourplanet.model.PlanetarySystem
import com.iesvirgendelcarmen.meetyourplanet.model.Resource
import com.iesvirgendelcarmen.meetyourplanet.model.SystemViewModel
import kotlinx.android.synthetic.main.fragment_systems_detail.*

class SystemDetailFragment(private val system: PlanetarySystem): Fragment() {

    lateinit var recyclerPlanets: RecyclerView
    lateinit var planetAdapter: PlanetRecyclerAdapter
    val planetViewModel: SystemViewModel by lazy {
        ViewModelProviders.of(this).get(SystemViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_systems_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textViewDetailTitle.text = system.star
        textViewConstellationDetailTitle.text = system.constellation
        textViewDistanceDetailTitle.text = "${system.distanceFromEarth} ${getString(R.string.auUnit)}"
        Glide
            .with(view.context)
            .load(system.imageURL)
            .centerCrop()
            .placeholder(R.drawable.placeholder)
            .into(imageViewSystemCoverDetail)

        planetAdapter = PlanetRecyclerAdapter(emptyList())
        recyclerPlanets = view.findViewById<RecyclerView>(R.id.planetsRecyclerView).apply{
            adapter = planetAdapter
            layoutManager = GridLayoutManager(context, 2, LinearLayoutManager.VERTICAL, false)
        }
    }

    override fun onStart() {
        super.onStart()
        planetViewModel.getAllPlanetsBySystemId(system._id)
        planetListObserver()
    }

    fun planetListObserver(){
        planetViewModel.planetsLiveData.observe(viewLifecycleOwner, Observer{
                resource ->
            when(resource.status){
                Resource.Status.SUCCESS -> {
                    planetAdapter.planets = resource.data
                    planetAdapter.notifyDataSetChanged()
                }
                Resource.Status.ERROR -> {
                    if(resource.message != null ){
                        Toast.makeText(context, resource.message , Toast.LENGTH_LONG).show()
                    }
                }
                Resource.Status.LOADING -> {
                    Toast.makeText(context, getString(R.string.loadingSystems), Toast.LENGTH_LONG).show()

                }
            }
        })
    }
}