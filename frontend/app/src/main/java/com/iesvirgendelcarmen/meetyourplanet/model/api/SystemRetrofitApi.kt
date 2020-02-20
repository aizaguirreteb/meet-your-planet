package com.iesvirgendelcarmen.meetyourplanet.model.api

import com.iesvirgendelcarmen.meetyourplanet.model.Planet
import com.iesvirgendelcarmen.meetyourplanet.model.PlanetarySystem
import retrofit2.Call
import retrofit2.http.*

interface SystemRetrofitApi {

    //PLANETARY SYSTEM

    @GET("api/systems")
    fun getAllPlanetarySystems() : Call<List<PlanetarySystem>>

    @GET("api/systems/{id}")
    fun getPlanetarySystemById(@Path("id") id: String) : Call<PlanetarySystem>

    @DELETE("api/systems/{id}")
    fun deletePlanetarySystemById(@Path("id") id: String) : Call<Unit>

    @POST("api/systems")
    fun addPlanetarySystem(@Body planetarySystem: PlanetarySystem) : Call<PlanetarySystem>

    @PUT("api/systems/{id}")
    fun updatePlanetarySystem(@Path("id") id: String, @Body planetarySystem: PlanetarySystem) : Call<PlanetarySystem>

    //PLANETS

    @GET("api/systems/{id}/planets")
    fun getPlanetsBySystemId(@Path("id") id: String) : Call<List<Planet>>

    @DELETE("api/planets/{id}")
    fun deletePlanetById(@Path("id") id: String) : Call<Unit>

    @POST("api/planets")
    fun addPlanet(@Body planet: Planet) : Call<Planet>

    @PUT("api/planets/{id}")
    fun updatePlanet(@Path("id") id: String, @Body planet: Planet) : Call<Planet>


}