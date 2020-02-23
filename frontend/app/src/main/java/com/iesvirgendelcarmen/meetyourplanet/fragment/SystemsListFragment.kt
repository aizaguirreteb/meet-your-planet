package com.iesvirgendelcarmen.meetyourplanet.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.iesvirgendelcarmen.meetyourplanet.MainActivity

import com.iesvirgendelcarmen.meetyourplanet.R
import com.iesvirgendelcarmen.meetyourplanet.adapters.SystemRecyclerAdapter
import com.iesvirgendelcarmen.meetyourplanet.model.PlanetarySystem
import com.iesvirgendelcarmen.meetyourplanet.model.Resource
import com.iesvirgendelcarmen.meetyourplanet.model.SystemViewModel
import kotlinx.android.synthetic.main.fragment_systems_list.*
import java.nio.BufferUnderflowException


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
        return inflater.inflate(R.layout.fragment_systems_list, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val clickListener = object: SystemRecyclerAdapter.OnItemClickListener{

            override fun onClicked(planetarySystem: PlanetarySystem) {
                Toast.makeText(context,
                    "${planetarySystem.star} + ${planetarySystem._id}",
                    Toast.LENGTH_SHORT).show()

                val systemDetailFragment = SystemDetailFragment()
                val args = Bundle()
                args.putParcelable("SYSTEM_CLICKED", planetarySystem)
                systemDetailFragment.arguments = args

                (activity as MainActivity).changeFragment(systemDetailFragment)
            }
        }

        val longClickListener = object : SystemRecyclerAdapter.OnItemLongClickListener {
            override fun onLongClicked(planetarySystem: PlanetarySystem) {
                val builderAction = AlertDialog.Builder(context!!)
                builderAction.setMessage(getString(R.string.whatToDoSystem))
                    .setPositiveButton(
                        getString(R.string.edit)
                    ) { dialog, _ ->
                        editSystem(planetarySystem)
                        dialog.dismiss()
                    }
                    .setNegativeButton(
                        getString(R.string.delete)
                    ) { dialog, _ ->
                        val builderDelete = AlertDialog.Builder(context!!)
                        builderDelete.setMessage(getString(R.string.deleteSystemQuestion))
                            .setPositiveButton(
                                getString(R.string.yes)
                            ) { dialog, _ ->
                                Toast.makeText(context,
                                    "${getString(R.string.deleting)} ${planetarySystem.star}",
                                    Toast.LENGTH_SHORT).show()
                                systemViewModel.deletePlanetarySystemById(planetarySystem._id)
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
        myAdapter = SystemRecyclerAdapter(emptyList(), clickListener, longClickListener)
        recyclerView = view.findViewById<RecyclerView>(R.id.fragment_recycler_view).apply{
            adapter = myAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }

        fabListSystemAdd.setOnClickListener {
            (activity as MainActivity).changeFragment(SystemFormFragment())
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
                    Toast.makeText(context, getString(R.string.loadingSystems), Toast.LENGTH_LONG).show()

                }
            }
        })
    }

    fun editSystem(planetarySystem: PlanetarySystem) {
        val editSystemFragment = SystemFormFragment()
        val args = Bundle()
        args.putParcelable("PLANETARYSYSTEM", planetarySystem)
        editSystemFragment.arguments = args
        (activity as MainActivity).changeFragment(editSystemFragment)
    }

}
