package com.iesvirgendelcarmen.meetyourplanet.model.api

import com.iesvirgendelcarmen.meetyourplanet.config.ApiConfig
import com.iesvirgendelcarmen.meetyourplanet.model.PlanetarySystem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

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

    fun updatePlanetarySystem(id: String, planetarySystem: PlanetarySystem, callback: PlanetarySystemRepositoryCallback) {
        val call = api.updatePlanetarySystem(id, planetarySystem)
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
}