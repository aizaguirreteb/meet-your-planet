package com.iesvirgendelcarmen.meetyourplanet.model

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.iesvirgendelcarmen.meetyourplanet.R
import com.iesvirgendelcarmen.meetyourplanet.model.api.PlanetarySystemRepository

class SystemViewModel : ViewModel(){

    val systemListLiveData = MutableLiveData<Resource<List<PlanetarySystem>>>()

    fun getAllPlanetarySystems() {
        PlanetarySystemRepository.getPlanetarySystems(object : PlanetarySystemRepository.PlanetarySystemListRepositoryCallback{
            override fun onPlanetarySystemResponse(planetarySystems: List<PlanetarySystem>) {
                systemListLiveData.value = Resource.success(planetarySystems)
            }

            override fun onPlanetarySystemError(msg: String?) {
                systemListLiveData.value = Resource.error(msg.orEmpty(), emptyList())
            }

            override fun onPlanetarySystemLoading() {
                systemListLiveData.value = Resource.loading(emptyList())
            }

        })
    }

    fun deletePlanetarySystemById(id: String) {
        PlanetarySystemRepository.deletePlanetarySystemById(id,
            object : PlanetarySystemRepository.PlanetarySystemListRepositoryCallback {
                override fun onPlanetarySystemResponse(planetarySystems: List<PlanetarySystem>) {
                    systemListLiveData.value = Resource.success(planetarySystems)
                }

                override fun onPlanetarySystemError(msg: String?) {
                    systemListLiveData.value = Resource.error(msg.orEmpty(), emptyList())
                }

                override fun onPlanetarySystemLoading() {
                }
            })
    }

    fun addPlanetarySystem(planetarySystem: PlanetarySystem){
        PlanetarySystemRepository.addPlanetarySystem(planetarySystem,
            object : PlanetarySystemRepository.PlanetarySystemRepositoryCallback {
                override fun onPlanetarySystemResponse(planetarySystem: PlanetarySystem) {

                }

                override fun onPlanetarySystemError(msg: String?) {
                    systemListLiveData.value = Resource.error(msg.orEmpty(), emptyList())
                }

                override fun onPlanetarySystemLoading() {
                }

            })
    }

    fun updatePlanetarySystem(id: String, planetarySystem: PlanetarySystem) {
        PlanetarySystemRepository.updatePlanetarySystem(id, planetarySystem,
            object : PlanetarySystemRepository.PlanetarySystemRepositoryCallback {
                override fun onPlanetarySystemResponse(planetarySystem: PlanetarySystem) {

                }

                override fun onPlanetarySystemError(msg: String?) {
                    systemListLiveData.value = Resource.error(msg.orEmpty(), emptyList())
                }

                override fun onPlanetarySystemLoading() {
                }

            })
    }

}