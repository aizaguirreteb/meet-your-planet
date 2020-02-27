package com.iesvirgendelcarmen.meetyourplanet.fragment

import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.iesvirgendelcarmen.meetyourplanet.MainActivity
import com.iesvirgendelcarmen.meetyourplanet.R
import com.iesvirgendelcarmen.meetyourplanet.adapters.PlanetRecyclerAdapter
import com.iesvirgendelcarmen.meetyourplanet.adapters.SystemRecyclerAdapter
import com.iesvirgendelcarmen.meetyourplanet.model.Planet
import com.iesvirgendelcarmen.meetyourplanet.model.PlanetarySystem
import com.iesvirgendelcarmen.meetyourplanet.model.Resource
import com.iesvirgendelcarmen.meetyourplanet.model.SystemViewModel
import kotlinx.android.synthetic.main.fragment_systems_detail.*

class SystemDetailFragment(): Fragment() {

    lateinit var system: PlanetarySystem

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
        system = arguments!!.get("SYSTEM_CLICKED") as PlanetarySystem

        textViewDetailTitle.text = system.star
        textViewConstellationDetailTitle.text = system.constellation
        textViewDistanceDetailTitle.text = "${system.distanceFromEarth} ${getString(R.string.auUnit)}"
        Glide
            .with(view.context)
            .load(system.imageURL)
            .centerCrop()
            .placeholder(R.drawable.placeholder)
            .into(imageViewSystemCoverDetail)

        val clickListener = object: PlanetRecyclerAdapter.OnItemClickListener{

            override fun onClicked(planet: Planet) {
                /*Toast.makeText(context,
                    "${planet.name} + ${planet._id}",
                    Toast.LENGTH_SHORT).show()*/

                val fragment = PlanetDetailFragment()
                val args = Bundle()
                args.putParcelable("PLANET_CLICKED", planet)
                fragment.arguments = args
                (activity as MainActivity).changeFragment(fragment)
            }
        }


        val longClickListener = object : PlanetRecyclerAdapter.OnItemLongClickListener {


            override fun onLongClicked(planet: Planet) {
                val builderAction = AlertDialog.Builder(context!!)
                builderAction.setMessage(getString(R.string.whatToDoPlanet))
                    .setPositiveButton(
                        getString(R.string.edit)
                    ) { dialog, _ ->
                        editPlanet(planet)
                        dialog.dismiss()
                    }
                    .setNegativeButton(
                        getString(R.string.delete)
                    ) { dialog, _ ->
                        val builderDelete = AlertDialog.Builder(context!!)
                        builderDelete.setMessage(getString(R.string.deletePlanetQuestion))
                            .setPositiveButton(
                                getString(R.string.yes)
                            ) { dialog, _ ->
                                Toast.makeText(
                                    context,
                                    "${getString(R.string.deleting)} ${planet.name}",
                                    Toast.LENGTH_SHORT
                                ).show()
                                planetViewModel.deletePlanetById(system._id, planet._id)
                                planetListObserver()
                                dialog.dismiss()
                            }
                            .setNegativeButton(
                                getString(R.string.cancel)
                            ) { dialog, _ ->
                                dialog.dismiss()
                            }
                        builderDelete.create()
                        builderDelete.show()
                        dialog.dismiss()
                    }
                builderAction.create()
                builderAction.show()
            }
        }

        fabAddPlanet.setOnClickListener {
            val fragmentPlanetForm = PlanetsFormFragment()
            val args = Bundle()
            args.putString("SYSTEM_ID", system._id)
            fragmentPlanetForm.arguments = args
            (activity as MainActivity).changeFragment(fragmentPlanetForm)
        }


            planetAdapter = PlanetRecyclerAdapter(emptyList(),clickListener, longClickListener)

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




    fun editPlanet(planet: Planet) {
        val editPlanetFragment = PlanetsFormFragment()
        val args = Bundle()
        args.putParcelable("PLANET", planet)
        editPlanetFragment.arguments = args
        (activity as MainActivity).changeFragment(editPlanetFragment)
    }
}