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

}