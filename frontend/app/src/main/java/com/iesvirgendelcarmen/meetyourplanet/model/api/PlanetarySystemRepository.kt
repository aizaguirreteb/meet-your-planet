package com.iesvirgendelcarmen.meetyourplanet.model.api

import android.util.Log
import com.iesvirgendelcarmen.meetyourplanet.config.ApiConfig
import com.iesvirgendelcarmen.meetyourplanet.model.Planet
import com.iesvirgendelcarmen.meetyourplanet.model.PlanetarySystem
import com.iesvirgendelcarmen.meetyourplanet.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.regex.Pattern

object PlanetarySystemRepository : PlanetarySystemsApi{

    private val api : SystemRetrofitApi

    init {
        val retrofit = Retrofit
            .Builder()
            .baseUrl(ApiConfig.API_URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        api = retrofit.create(SystemRetrofitApi::class.java)

    }

    override fun getPlanetarySystems(callback: PlanetarySystemListRepositoryCallback){
        callback.onPlanetarySystemLoading()
        val call = api.getAllPlanetarySystems(ApiConfig.token)
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
        val call = api.getPlanetarySystemById(id, ApiConfig.token)
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

    override fun deletePlanetarySystemById(id: String, callback: PlanetarySystemListRepositoryCallback){
        val call = api.deletePlanetarySystemById(id, ApiConfig.token)
        call.enqueue(object : Callback<Unit>{
            override fun onFailure(call: Call<Unit>, t: Throwable) {
                callback.onPlanetarySystemError(t.message)
            }

            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                getPlanetarySystems(callback)
            }

        })
    }

    override fun addPlanetarySystem(planetarySystem: PlanetarySystem, callback: PlanetarySystemRepositoryCallback) {
        val call = api.addPlanetarySystem(planetarySystem, ApiConfig.token)
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

    override fun updatePlanetarySystem(id: String, planetarySystem: PlanetarySystem, callback: RepositoryUpdateCallback) {
        val call = api.updatePlanetarySystem(id, planetarySystem, ApiConfig.token)
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

    override fun getPlanetsBySystemId(id: String, callback: PlanetsListRepositoryCallback){
        callback.onPlanetsLoading()
        val call = api.getPlanetsBySystemId(id, ApiConfig.token)
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

    override fun deletePlanetById(systemId: String, id: String, callback: PlanetsListRepositoryCallback){
        val call = api.deletePlanetById(id, ApiConfig.token)
        call.enqueue(object : Callback<Unit>{
            override fun onFailure(call: Call<Unit>, t: Throwable) {
                callback.onPlanetsError(t.message)
            }

            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                getPlanetsBySystemId(systemId, callback)
            }

        })
    }

    override fun addPlanet(planet: Planet, callback: PlanetRepositoryCallback) {
        val call = api.addPlanet(planet, ApiConfig.token)
        call.enqueue(object : Callback<Planet>{
            override fun onFailure(call: Call<Planet>, t: Throwable) {
                callback.onPlanetError(t.message)
            }

            override fun onResponse(
                call: Call<Planet>,
                response: Response<Planet>
            ) {
                var systemResponse = response.body()
                if (systemResponse == null) {
                    systemResponse = Planet( "", "", "", 0.0, "", 0,
                        0, "", "", "", "")
                }
                callback.onPlanetResponse(systemResponse)
            }

        })
    }

    override fun updatePlanet(id: String, planet: Planet, callback: RepositoryUpdateCallback) {
        val call = api.updatePlanet(id, planet, ApiConfig.token)
        call.enqueue(object : Callback<Planet>{
            override fun onFailure(call: Call<Planet>, t: Throwable) {
                callback.onPlanetarySystemError(t.message)
            }

            override fun onResponse(
                call: Call<Planet>,
                response: Response<Planet>
            ) {
                callback.onPlanetarySystemResponse(response.message())
            }

        })
    }

    //USERS

    override fun login(user: User, callback: UserRepositoryCallback) {
        val call = api.login(user)
        call.enqueue(object: Callback<Any> {
            override fun onFailure(call: Call<Any>, t: Throwable) {
                Log.d("LOGIN FAIL!!", t.message.orEmpty())
            }

            override fun onResponse(call: Call<Any>, response: Response<Any>) {
                Log.d("LOGIN!!", response.body().toString())
                if (response.body().toString() != "null"){
                    var token = response.body().toString().substringAfter(" token=")
                    token = token.substringBefore("}}")
                    callback.onUserResponse(token)
                    Log.d("LOGINNN", token)
                }
            }

        })
    }


    interface PlanetRepositoryCallback{
        fun onPlanetResponse(planet:Planet)
        fun onPlanetError(msg: String?)
        fun onPlanetLoading()
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

    interface UserRepositoryCallback {
        fun onUserResponse(obj: Any)
        fun onUserError(msg: String?)
        fun onUserLoading()
    }
}