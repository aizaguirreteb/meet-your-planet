package com.iesvirgendelcarmen.meetyourplanet.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.iesvirgendelcarmen.meetyourplanet.R
import com.iesvirgendelcarmen.meetyourplanet.adapters.SystemRecyclerAdapter
import com.iesvirgendelcarmen.meetyourplanet.model.PlanetarySystem
import com.iesvirgendelcarmen.meetyourplanet.model.Resource
import com.iesvirgendelcarmen.meetyourplanet.model.SystemViewModel


class SystemsListFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var myAdapter: SystemRecyclerAdapter
    val systemViewModel: SystemViewModel by lazy {
        ViewModelProviders.of(this).get(SystemViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_systems_list, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var clickListener = object: SystemRecyclerAdapter.OnItemClickListener{

            override fun onClicked(planetarySystem: PlanetarySystem) {
                Toast.makeText(context,
                    "${planetarySystem.star} + ${planetarySystem._id}",
                    Toast.LENGTH_SHORT).show()
            }
        }

        var longClickListener = object : SystemRecyclerAdapter.OnItemLongClickListener {
            override fun onLongClicked(planetarySystem: PlanetarySystem) {
                Toast.makeText(context,
                    "Borrando ${planetarySystem.star} + ${planetarySystem._id}",
                    Toast.LENGTH_SHORT).show()
            }

        }
        myAdapter = SystemRecyclerAdapter(emptyList(), clickListener, longClickListener)
        recyclerView = view.findViewById<RecyclerView>(R.id.fragment_recycler_view).apply{
            adapter = myAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }

    }

    override fun onStart() {
        super.onStart()
        observeSystemList()
        systemViewModel.getAllPlanetarySystems()

    }

    private fun observeSystemList(){
        systemViewModel.systemListLiveData.observe(viewLifecycleOwner, Observer {
                resource ->
            when(resource.status){
                Resource.Status.SUCCESS -> {
                    myAdapter.systems = resource.data
                    myAdapter.notifyDataSetChanged()
                }
                Resource.Status.ERROR -> {
                    if(resource.message != null ){
                        Toast.makeText(context, resource.message , Toast.LENGTH_LONG).show()
                    }
                }
                Resource.Status.LOADING -> {
                    Toast.makeText(context, "Loading Systems" , Toast.LENGTH_LONG).show()

                }
            }
        })
    }

}
