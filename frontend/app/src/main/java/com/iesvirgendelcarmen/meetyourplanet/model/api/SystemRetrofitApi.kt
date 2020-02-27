package com.iesvirgendelcarmen.meetyourplanet.model.api

import com.iesvirgendelcarmen.meetyourplanet.model.Planet
import com.iesvirgendelcarmen.meetyourplanet.model.PlanetarySystem
import com.iesvirgendelcarmen.meetyourplanet.model.User
import retrofit2.Call
import retrofit2.http.*

interface SystemRetrofitApi {

    //PLANETARY SYSTEM

    @GET("api/systems")
    fun getAllPlanetarySystems(@Header("x-access-token") token: String) : Call<List<PlanetarySystem>>

    @GET("api/systems/{id}")
    fun getPlanetarySystemById(@Path("id") id: String, @Header("x-access-token") token: String) : Call<PlanetarySystem>

    @DELETE("api/systems/{id}")
    fun deletePlanetarySystemById(@Path("id") id: String, @Header("x-access-token") token: String) : Call<Unit>

    @POST("api/systems")
    fun addPlanetarySystem(@Body planetarySystem: PlanetarySystem, @Header("x-access-token") token: String) : Call<PlanetarySystem>

    @PUT("api/systems/{id}")
    fun updatePlanetarySystem(@Path("id") id: String, @Body planetarySystem: PlanetarySystem, @Header("x-access-token") token: String) : Call<PlanetarySystem>

    //PLANETS

    @GET("api/systems/{id}/planets")
    fun getPlanetsBySystemId(@Path("id") id: String, @Header("x-access-token") token: String) : Call<List<Planet>>

    @DELETE("api/planets/{id}")
    fun deletePlanetById(@Path("id") id: String, @Header("x-access-token") token: String) : Call<Unit>

    @POST("api/planets")
    fun addPlanet(@Body planet: Planet, @Header("x-access-token") token: String) : Call<Planet>

    @PUT("api/planets/{id}")
    fun updatePlanet(@Path("id") id: String, @Body planet: Planet, @Header("x-access-token") token: String) : Call<Planet>

    //USERS
    @POST("api/login")
    fun login(@Body user: User) : Call<Any>


}