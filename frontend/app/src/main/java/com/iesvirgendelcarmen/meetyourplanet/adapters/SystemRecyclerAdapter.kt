package com.iesvirgendelcarmen.meetyourplanet.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.iesvirgendelcarmen.meetyourplanet.R
import com.iesvirgendelcarmen.meetyourplanet.model.PlanetarySystem

class SystemRecyclerAdapter (var systems: List<PlanetarySystem>,
                             val itemClickListener: OnItemClickListener,
                             val itemLongCLickListener: OnItemLongClickListener):
RecyclerView.Adapter<SystemRecyclerAdapter.SystemViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SystemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.system_recyclerview_item, parent, false)
        return SystemViewHolder(view)
    }

    override fun getItemCount() = systems.size

    override fun onBindViewHolder(holder: SystemViewHolder, position: Int) {
         val system = systems[position]
        holder.bind(system, itemClickListener, itemLongCLickListener)
    }

    inner class SystemViewHolder(view: View): RecyclerView.ViewHolder(view){
        val systemTitle = itemView.findViewById<TextView>(R.id.systemTitle)
        val systemConstellation = itemView.findViewById<TextView>(R.id.systemConstellation)
        val systemDistance = itemView.findViewById<TextView>(R.id.systemDistanceEarth)
        val systemImage = itemView.findViewById<ImageView>(R.id.imageViewCoverR)


        fun bind(planetarySystem: PlanetarySystem, clickListener:OnItemClickListener, longClickListener: OnItemLongClickListener){
            systemTitle.text = planetarySystem.star
            systemConstellation.text = planetarySystem.constellation
            systemDistance.text = "${planetarySystem.distanceFromEarth} ${itemView.resources.getString(R.string.auUnit)}"


            Glide
                .with(itemView.context)
                .load(planetarySystem.imageURL)
                .centerCrop()
                .placeholder(R.drawable.placeholder)
                .into(systemImage)

            itemView.setOnClickListener{
                clickListener.onClicked(planetarySystem)
            }

            itemView.setOnLongClickListener {
                longClickListener.onLongClicked(planetarySystem)
                true
            }
        }
    }

    interface OnItemClickListener{
        fun onClicked(planetarySystem: PlanetarySystem)
    }

    interface OnItemLongClickListener{
        fun onLongClicked(planetarySystem: PlanetarySystem)
    }

}