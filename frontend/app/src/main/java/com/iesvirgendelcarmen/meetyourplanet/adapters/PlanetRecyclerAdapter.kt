package com.iesvirgendelcarmen.meetyourplanet.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.iesvirgendelcarmen.meetyourplanet.R
import com.iesvirgendelcarmen.meetyourplanet.model.Planet

class PlanetRecyclerAdapter(var planets: List<Planet>) :
    RecyclerView.Adapter<PlanetRecyclerAdapter.PlanetViewHolder>() {

    inner class PlanetViewHolder(view: View): RecyclerView.ViewHolder(view){
        var planetImage = view.findViewById<ImageView>(R.id.imagePlanet)
        var planetTitle = view.findViewById<TextView>(R.id.planetTitle)

        fun bind(planet: Planet){
            planetTitle.text = planet.name
            Glide
                .with(itemView.context)
                .load(planet.planetImage)
                .centerCrop()
                .placeholder(R.drawable.placeholder)
                .into(planetImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanetViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.planet_recyclerview_item, parent, false)
        return PlanetViewHolder(view)
    }

    override fun getItemCount() = planets.size

    override fun onBindViewHolder(holder: PlanetViewHolder, position: Int) {
        val planet = planets[position]
        holder.bind(planet)
    }

}