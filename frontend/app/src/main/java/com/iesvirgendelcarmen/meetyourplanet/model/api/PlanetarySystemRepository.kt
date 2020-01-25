package com.iesvirgendelcarmen.meetyourplanet.model.api

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
            .baseUrl("http://IP:3000/")
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



    interface PlanetarySystemListRepositoryCallback{
        fun onPlanetarySystemResponse(planetarySystems: List<PlanetarySystem>)
        fun onPlanetarySystemError(msg: String?)
        fun onPlanetarySystemLoading()
    }



}