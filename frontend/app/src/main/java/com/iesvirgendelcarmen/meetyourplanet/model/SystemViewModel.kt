package com.iesvirgendelcarmen.meetyourplanet.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.iesvirgendelcarmen.meetyourplanet.config.ApiConfig
import com.iesvirgendelcarmen.meetyourplanet.model.api.PlanetarySystemRepository

class SystemViewModel : ViewModel(){

    val systemListLiveData = MutableLiveData<Resource<List<PlanetarySystem>>>()
    val planetsLiveData = MutableLiveData<Resource<List<Planet>>>()
    val loginLiveData = MutableLiveData<Boolean>()

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
            object : PlanetarySystemRepository.RepositoryUpdateCallback {
                override fun onPlanetarySystemResponse(msg: String?) {

                }

                override fun onPlanetarySystemError(msg: String?) {
                    systemListLiveData.value = Resource.error(msg.orEmpty(), emptyList())
                }

                override fun onPlanetarySystemLoading() {
                }

            })
    }

    fun getAllPlanetsBySystemId(id: String){
        PlanetarySystemRepository.getPlanetsBySystemId(id, object : PlanetarySystemRepository.PlanetsListRepositoryCallback{
            override fun onPLanetsResponse(planets: List<Planet>) {
                planetsLiveData.value = Resource.success(planets)
            }

            override fun onPlanetsError(msg: String?) {
                planetsLiveData.value = Resource.error(msg.orEmpty(), emptyList())
            }

            override fun onPlanetsLoading() {

            }


        })
    }

    fun deletePlanetById(systemId: String, id: String) {
        PlanetarySystemRepository.deletePlanetById(systemId,id,
            object : PlanetarySystemRepository.PlanetsListRepositoryCallback{
                override fun onPLanetsResponse(planets: List<Planet>) {
                    planetsLiveData.value = Resource.success(planets)
                }


                override fun onPlanetsError(msg: String?) {
                    planetsLiveData.value = Resource.error(msg.orEmpty(), emptyList())
                }

                override fun onPlanetsLoading() {

                }

            })
    }

    fun addPlanet(planet: Planet) {
        PlanetarySystemRepository.addPlanet(planet,
            object : PlanetarySystemRepository.PlanetRepositoryCallback {
                override fun onPlanetResponse(planet: Planet){
                }

                override fun onPlanetError(msg: String?) {
                    systemListLiveData.value = Resource.error(msg.orEmpty(), emptyList())
                }

                override fun onPlanetLoading() {
                }

            })
    }

    fun updatePlanet(id: String, planet: Planet) {
        PlanetarySystemRepository.updatePlanet(id, planet,
            object : PlanetarySystemRepository.RepositoryUpdateCallback {
                override fun onPlanetarySystemResponse(msg: String?) {

                }

                override fun onPlanetarySystemError(msg: String?) {
                    systemListLiveData.value = Resource.error(msg.orEmpty(), emptyList())
                }

                override fun onPlanetarySystemLoading() {
                }

            })
    }

    fun login(user: User){
        PlanetarySystemRepository.login(user, object : PlanetarySystemRepository.UserRepositoryCallback {
            override fun onUserResponse(obj: Any) {
                Log.d("VM R", obj.toString())
                ApiConfig.token = obj.toString()
                loginLiveData.value = true
            }

            override fun onUserError(msg: String?) {
                Log.d("VM E", msg.orEmpty())
            }

            override fun onUserLoading() {
            }

        })
    }
}