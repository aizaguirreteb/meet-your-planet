package com.iesvirgendelcarmen.meetyourplanet.model.api

import com.iesvirgendelcarmen.meetyourplanet.config.ApiConfig
import com.iesvirgendelcarmen.meetyourplanet.model.Planet
import com.iesvirgendelcarmen.meetyourplanet.model.PlanetarySystem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PlanetarySystemRepository {

    private val api : SystemRetrofitApi

    init {
        val retrofit = Retrofit
            .Builder()
            .baseUrl(ApiConfig.API_URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        api = retrofit.create(SystemRetrofitApi::class.java)

    }

    fun getPlanetarySystems(callback: PlanetarySystemListRepositoryCallback){
        callback.onPlanetarySystemLoading()
        val call = api.getAllPlanetarySystems()
        call.enqueue(object: Callback<List<PlanetarySystem>>{
            override fun onFailure(call: Call<List<PlanetarySystem>>, t: Throwable) {
                callback.onPlanetarySystemError(t.message)
            }

            override fun onResponse(
                call: Call<List<PlanetarySystem>>,
                response: Response<List<PlanetarySystem>>
            ) {
                val systemResponse = response.body().orEmpty()
                callback.onPlanetarySystemResponse(systemResponse)
            }

        })
    }

    fun getSystemById(id: String, callback: PlanetarySystemRepositoryCallback){
        callback.onPlanetarySystemLoading()
        val call = api.getPlanetarySystemById(id)
        call.enqueue(object : Callback<PlanetarySystem>{
            override fun onFailure(call: Call<PlanetarySystem>, t: Throwable) {
                callback.onPlanetarySystemError(t.message)
            }

            override fun onResponse(
                call: Call<PlanetarySystem>,
                response: Response<PlanetarySystem>
            ) {
                var systemResponse = response.body()
                if (systemResponse == null) {
                    systemResponse = PlanetarySystem( "", "", "", 0.0, "")
                }
                callback.onPlanetarySystemResponse(systemResponse)
            }

        })
    }

    fun deletePlanetarySystemById(id: String, callback: PlanetarySystemListRepositoryCallback){
        val call = api.deletePlanetarySystemById(id)
        call.enqueue(object : Callback<Unit>{
            override fun onFailure(call: Call<Unit>, t: Throwable) {
                callback.onPlanetarySystemError(t.message)
            }

            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                getPlanetarySystems(callback)
            }

        })
    }

    fun addPlanetarySystem(planetarySystem: PlanetarySystem, callback: PlanetarySystemRepositoryCallback) {
        val call = api.addPlanetarySystem(planetarySystem)
        call.enqueue(object : Callback<PlanetarySystem>{
            override fun onFailure(call: Call<PlanetarySystem>, t: Throwable) {
                callback.onPlanetarySystemError(t.message)
            }

            override fun onResponse(
                call: Call<PlanetarySystem>,
                response: Response<PlanetarySystem>
            ) {
                var systemResponse = response.body()
                if (systemResponse == null) {
                    systemResponse = PlanetarySystem( "", "", "", 0.0, "")
                }
                callback.onPlanetarySystemResponse(systemResponse)
            }

        })
    }

    fun updatePlanetarySystem(id: String, planetarySystem: PlanetarySystem, callback: RepositoryUpdateCallback) {
        val call = api.updatePlanetarySystem(id, planetarySystem)
        call.enqueue(object : Callback<PlanetarySystem>{
            override fun onFailure(call: Call<PlanetarySystem>, t: Throwable) {
                callback.onPlanetarySystemError(t.message)
            }

            override fun onResponse(
                call: Call<PlanetarySystem>,
                response: Response<PlanetarySystem>
            ) {
                callback.onPlanetarySystemResponse(response.message())
            }

        })
    }

    //PLANETS

    fun getPlanetsBySystemId(id: String, callback: PlanetsListRepositoryCallback){
        callback.onPlanetsLoading()
        val call = api.getPlanetsBySystemId(id)
        call.enqueue(object : Callback<List<Planet>> {
            override fun onFailure(call: Call<List<Planet>>, t: Throwable) {
                callback.onPlanetsError(t.message)
            }

            override fun onResponse(call: Call<List<Planet>>, response: Response<List<Planet>>) {
                var planetsResponse = response.body().orEmpty()
                callback.onPLanetsResponse(planetsResponse)
            }

        })
    }

    interface PlanetsListRepositoryCallback {
        fun onPLanetsResponse(planets: List<Planet>)
        fun onPlanetsError(msg: String?)
        fun onPlanetsLoading()
    }

    interface PlanetarySystemListRepositoryCallback{
        fun onPlanetarySystemResponse(planetarySystems: List<PlanetarySystem>)
        fun onPlanetarySystemError(msg: String?)
        fun onPlanetarySystemLoading()
    }

    interface PlanetarySystemRepositoryCallback{
        fun onPlanetarySystemResponse(planetarySystem: PlanetarySystem)
        fun onPlanetarySystemError(msg: String?)
        fun onPlanetarySystemLoading()
    }

    interface RepositoryUpdateCallback{
        fun onPlanetarySystemResponse(msg: String?)
        fun onPlanetarySystemError(msg: String?)
        fun onPlanetarySystemLoading()
    }
}