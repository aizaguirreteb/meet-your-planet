package com.iesvirgendelcarmen.meetyourplanet.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.iesvirgendelcarmen.meetyourplanet.R
import com.iesvirgendelcarmen.meetyourplanet.model.PlanetarySystem
import com.iesvirgendelcarmen.meetyourplanet.model.SystemViewModel
import kotlinx.android.synthetic.main.fragment_systems_form.*

class SystemFormFragment : Fragment() {

    val systemViewModel: SystemViewModel by lazy {
        ViewModelProviders.of(this).get(SystemViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //need arguments for edit
        return inflater.inflate(R.layout.fragment_systems_form, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnFormSystemSubmit.text = getString(R.string.add_system)

        btnFormSystemSubmit.setOnClickListener {
            addPlanetarySystem()
        }
    }

    fun getPlanetarySystem() : PlanetarySystem {
        val star = etFormStar.text.toString()
        val constellation = etFormConstellation.text.toString()
        val distance = etFormDistance.text.toString().toDouble()
        val imageURL = etFormImageURL.text.toString()

        return if (star == "" || constellation == "" || distance == 0.0 || star == "")
            PlanetarySystem("-1", "", "", 0.0, "")
        else
            PlanetarySystem("0", star, constellation, distance, imageURL)
    }

    fun addPlanetarySystem() {
        val planetarySystem = getPlanetarySystem()
        if (planetarySystem._id == "-1")
            Toast.makeText(context, getString(R.string.system_form_field_error), Toast.LENGTH_SHORT).show()
        else {
            systemViewModel.addPlanetarySystem(planetarySystem)
            Toast.makeText(context, getString(R.string.system_form_field_adding), Toast.LENGTH_SHORT).show()
        }

    }
}