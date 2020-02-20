package com.iesvirgendelcarmen.meetyourplanet.fragment

import android.media.Image
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Surface
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders

import com.iesvirgendelcarmen.meetyourplanet.R
import com.iesvirgendelcarmen.meetyourplanet.model.Planet
import com.iesvirgendelcarmen.meetyourplanet.model.SystemViewModel
import kotlinx.android.synthetic.main.fragment_planets_form.*
import kotlinx.android.synthetic.main.fragment_planets_form.etFormOrbitalPeriod
import kotlinx.android.synthetic.main.fragment_planets_form.etFormPlanetImageURL
import kotlinx.android.synthetic.main.fragment_systems_form.*

class PlanetsFormFragment(private val systemId: String) : Fragment() {

    val surfaceArray = arrayOf("Gas", "Liquid", "Solida", "Mixed", "Unknown")

    private val viewModel: SystemViewModel by lazy {
        ViewModelProviders.of(this).get(SystemViewModel::class.java)
    }

    lateinit var planetEdit : Planet
    var inEditPlanetMode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (arguments != null) {
            planetEdit = arguments!!.get("PLANET") as Planet
            inEditPlanetMode = true
        }
        return inflater.inflate(R.layout.fragment_planets_form, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(!inEditPlanetMode) {
            btnPlanetSubmit.text = getString(R.string.add_planet)
            btnPlanetSubmit.setOnClickListener {
                addPlanet()
            }
        }
        else{
            btnPlanetSubmit.text = getString(R.string.update_planet)
            btnPlanetSubmit.setOnClickListener {
                updatePlanet()
            }
            setPlanetFields()
        }

        val genreAdapter = ArrayAdapter<String>(context!!, android.R.layout.simple_dropdown_item_1line, surfaceArray)
        spinnerSurface.adapter = genreAdapter
    }

    private fun setPlanetFields(){
        etFormName.setText(planetEdit.name)
        etFormCategory.setText(planetEdit.category)
        etFormOrbitalPeriod.setText(planetEdit.orbitalPeriod.toString())
        etFormPlanetImageURL.setText(planetEdit.planetImage)
        editTextGravity.setText(planetEdit.gravity)
        editTextMass.setText(planetEdit.gravity)
        editTextSatellites.setText(planetEdit.numberSatellites.toString())
        editTextTemperature.setText(planetEdit.equilibriumTemperature.toString())
        for( i in 0 until surfaceArray.size){ 
            if (surfaceArray[i].equals(planetEdit.surface)){   
                spinnerSurface.setSelection(i)             
            } else {                
                spinnerSurface.setSelection(0)     
            }
        }
        

    }

    private  fun getPlanet() : Planet{
        val name = etFormName.text.toString()
        val category = etFormCategory.text.toString()
        val orbitalPeriod = etFormOrbitalPeriod.text.toString().toDouble()
        val imagen = etFormPlanetImageURL.text.toString()
        val satellites = editTextSatellites.text.toString().toInt()
        val gravity = editTextGravity.text.toString()
        val mass = editTextMass.text.toString()
        val temperature = editTextTemperature.text.toString().toInt()
        val surface = spinnerSurface.selectedItem.toString()

        return  if(name =="" || category == "")
                    Planet("-1", "", "", 0.0, "", 0, 0, "",
                 "","", systemId)
                else
                    if(inEditPlanetMode)
                        Planet(planetEdit._id, name,category,orbitalPeriod,mass,temperature,satellites,gravity,surface,imagen, systemId)
                    else
                        Planet("0", name,category,orbitalPeriod,mass,temperature,satellites,gravity,surface,imagen, systemId)

    }

    private fun addPlanet() {
        val planet = getPlanet()
        if (planet._id == "-1")
            Toast.makeText(context, getString(R.string.system_form_field_error), Toast.LENGTH_SHORT).show()
        else {
            viewModel.addPlanet(planet)
            Toast.makeText(context, getString(R.string.planet_form_field_adding), Toast.LENGTH_SHORT).show()
        }

    }

    private fun updatePlanet() {
        val planet = getPlanet()
        if (planet._id == "-1")
            Toast.makeText(context, getString(R.string.system_form_field_error), Toast.LENGTH_SHORT).show()
        else {
            viewModel.updatePlanet(planet._id, planet)
            Toast.makeText(context, getString(R.string.system_form_field_updating), Toast.LENGTH_SHORT).show()
        }
    }


}
