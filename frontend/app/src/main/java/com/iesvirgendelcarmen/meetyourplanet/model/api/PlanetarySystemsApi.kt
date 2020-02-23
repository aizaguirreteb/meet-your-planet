package com.iesvirgendelcarmen.meetyourplanet.model.api

import com.iesvirgendelcarmen.meetyourplanet.model.Planet
import com.iesvirgendelcarmen.meetyourplanet.model.PlanetarySystem

interface PlanetarySystemsApi {

    //SYSTEMS

    fun getPlanetarySystems(callback: PlanetarySystemRepository.PlanetarySystemListRepositoryCallback)

    fun addPlanetarySystem(planetarySystem: PlanetarySystem, callback: PlanetarySystemRepository.PlanetarySystemRepositoryCallback)

    fun updatePlanetarySystem(id:String, planetarySystem: PlanetarySystem, callback: PlanetarySystemRepository.RepositoryUpdateCallback)

    fun deletePlanetarySystemById(id: String, callback: PlanetarySystemRepository.PlanetarySystemListRepositoryCallback)


    //PLANETS

    fun getPlanetsBySystemId(id: String, callback: PlanetarySystemRepository.PlanetsListRepositoryCallback)

    fun deletePlanetById(systemId: String, id: String, callback: PlanetarySystemRepository.PlanetsListRepositoryCallback)

    fun updatePlanet(id: String, planet: Planet, callback: PlanetarySystemRepository.RepositoryUpdateCallback)


    fun addPlanet(planet: Planet, callback: PlanetarySystemRepository.PlanetRepositoryCallback)

}