package com.iesvirgendelcarmen.meetyourplanet.model.api

import com.iesvirgendelcarmen.meetyourplanet.model.Planet
import com.iesvirgendelcarmen.meetyourplanet.model.PlanetarySystem
import retrofit2.Call
import retrofit2.http.*

interface SystemRetrofitApi {

    //PLANETARY SYSTEM

    @GET("api/systems")
    fun getAllPlanetarySystems() : Call<List<PlanetarySystem>>

    @GET("systems/{id}")
    fun getPlanetarySystemById(@Path("id") id: Int) : Call<PlanetarySystem>



}