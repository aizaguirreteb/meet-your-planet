package com.iesvirgendelcarmen.meetyourplanet.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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

}