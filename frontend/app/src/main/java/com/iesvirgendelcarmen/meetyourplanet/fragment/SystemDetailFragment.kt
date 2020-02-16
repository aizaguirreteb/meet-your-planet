package com.iesvirgendelcarmen.meetyourplanet.fragment

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.iesvirgendelcarmen.meetyourplanet.R
import com.iesvirgendelcarmen.meetyourplanet.model.PlanetarySystem
import kotlinx.android.synthetic.main.fragment_systems_detail.*

class SystemDetailFragment(private val system: PlanetarySystem): Fragment() {

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
    }
}