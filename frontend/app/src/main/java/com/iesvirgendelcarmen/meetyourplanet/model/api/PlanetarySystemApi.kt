package com.iesvirgendelcarmen.meetyourplanet.model.api

import com.iesvirgendelcarmen.meetyourplanet.model.Planet
import com.iesvirgendelcarmen.meetyourplanet.model.PlanetarySystem
import com.iesvirgendelcarmen.meetyourplanet.model.User

interface PlanetarySystemApi {

    //SYSTEMS

    fun getPlanetarySystems(callback: PlanetarySystemRetrofitRepository.PlanetarySystemListRepositoryCallback)

    fun addPlanetarySystem(planetarySystem: PlanetarySystem, callback: PlanetarySystemRetrofitRepository.PlanetarySystemRepositoryCallback)

    fun updatePlanetarySystem(id:String, planetarySystem: PlanetarySystem, callback: PlanetarySystemRetrofitRepository.RepositoryUpdateCallback)

    fun deletePlanetarySystemById(id: String, callback: PlanetarySystemRetrofitRepository.PlanetarySystemListRepositoryCallback)


    //PLANETS

    fun getPlanetsBySystemId(id: String, callback: PlanetarySystemRetrofitRepository.PlanetsListRepositoryCallback)

    fun deletePlanetById(systemId: String, id: String, callback: PlanetarySystemRetrofitRepository.PlanetsListRepositoryCallback)

    fun updatePlanet(id: String, planet: Planet, callback: PlanetarySystemRetrofitRepository.RepositoryUpdateCallback)

    fun addPlanet(planet: Planet, callback: PlanetarySystemRetrofitRepository.PlanetRepositoryCallback)

    //USERS

    fun login(user: User, callback: PlanetarySystemRetrofitRepository.UserRepositoryCallback)

}