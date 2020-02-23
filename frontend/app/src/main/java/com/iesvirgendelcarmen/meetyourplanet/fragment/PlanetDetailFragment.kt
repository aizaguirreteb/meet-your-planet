package com.iesvirgendelcarmen.meetyourplanet.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide

import com.iesvirgendelcarmen.meetyourplanet.R
import com.iesvirgendelcarmen.meetyourplanet.model.Planet
import kotlinx.android.synthetic.main.fragment_planet_detail.*


class PlanetDetailFragment() : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_planet_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var planet = arguments!!.get("PLANET_CLICKED") as Planet

        textViewPlanetDetailTitle.text = planet.name
        textViewCategory.text = planet.category
        textViewGravity.text = planet.gravity
        textViewSatellites.text = planet.numberSatellites.toString()
        textViewSurface.text = planet.surface
        textViewMass.text = planet.mass
        textViewTemperature.text = planet.equilibriumTemperature.toString() + " ºC"
        textViewOrbitalPeriod.text = planet.orbitalPeriod.toString()

        Glide
            .with(view.context)
            .load(planet.planetImage)
            .centerCrop()
            .placeholder(R.drawable.placeholder)
            .into(imageViewPlanetCoverDetail)
    }
}
