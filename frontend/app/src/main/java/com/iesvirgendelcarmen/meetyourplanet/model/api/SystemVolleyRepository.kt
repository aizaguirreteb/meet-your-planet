package com.iesvirgendelcarmen.meetyourplanet.model.api


import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.google.gson.Gson
import com.iesvirgendelcarmen.meetyourplanet.config.ApiConfig
import com.iesvirgendelcarmen.meetyourplanet.model.Planet
import com.iesvirgendelcarmen.meetyourplanet.model.PlanetarySystem
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject


object SystemVolleyRepository : PlanetarySystemsApi {

    override fun getPlanetarySystems(callback: PlanetarySystemRepository.PlanetarySystemListRepositoryCallback) {
        callback.onPlanetarySystemLoading()
        VolleySingleton.getInstance().requestQueue

        val stringRequest = StringRequest(
            Request.Method.GET,
            ApiConfig.API_URL_BASE + "api/systems",
            Response.Listener {
                response ->
                var strResp = response.toString()
                val jsonObj = JSONArray(strResp)
                val list = mutableListOf<PlanetarySystem>()
                for( i in 0 until jsonObj.length()) {
                    var obj = jsonObj.getJSONObject(i)
                    val resp = Gson().fromJson<PlanetarySystem>(
                        obj.toString(), PlanetarySystem::class.java
                    )
                    list.add(resp)

                }
                callback.onPlanetarySystemResponse(list)
            },
            Response.ErrorListener {
                error -> callback.onPlanetarySystemError(error.message)
            }
        )
        VolleySingleton.getInstance().addToRequestQueue(stringRequest)

    }

    override fun addPlanetarySystem(
        planetarySystem: PlanetarySystem,
        callback: PlanetarySystemRepository.PlanetarySystemRepositoryCallback
    ) {
        var jsonObj = JSONObject()
        jsonObj.put("_id", planetarySystem._id)
        jsonObj.put("star", planetarySystem.star)
        jsonObj.put("constellation", planetarySystem.constellation)
        jsonObj.put("distanceFromEarth", planetarySystem.distanceFromEarth)
        jsonObj.put("imageURL", planetarySystem.imageURL)

        VolleySingleton.getInstance().requestQueue

        val stringRequest = JsonObjectRequest(
            Request.Method.POST,
            ApiConfig.API_URL_BASE + "api/systems",
            jsonObj,
            Response.Listener {
                    response ->
                callback.onPlanetarySystemResponse(parseSystem(response.toString()))
            },
            Response.ErrorListener {
                error ->
                callback.onPlanetarySystemError(error.message)
            }

        )

        VolleySingleton.getInstance().addToRequestQueue(stringRequest)
    }

    override fun updatePlanetarySystem(
        id: String,
        planetarySystem: PlanetarySystem,
        callback: PlanetarySystemRepository.RepositoryUpdateCallback
    ) {
        var jsonObj = JSONObject()
        jsonObj.put("_id", planetarySystem._id)
        jsonObj.put("star", planetarySystem.star)
        jsonObj.put("constellation", planetarySystem.constellation)
        jsonObj.put("distanceFromEarth", planetarySystem.distanceFromEarth)
        jsonObj.put("imageURL", planetarySystem.imageURL)

        VolleySingleton.getInstance().requestQueue

        val stringRequest = JsonObjectRequest(
            Request.Method.PUT,
            ApiConfig.API_URL_BASE + "api/systems/${id}",
            jsonObj,
            Response.Listener {
                    response ->
                callback.onPlanetarySystemResponse(response.toString())
            },
            Response.ErrorListener {
                    error ->
                callback.onPlanetarySystemError(error.message)
            }

        )

        VolleySingleton.getInstance().addToRequestQueue(stringRequest)    }

    override fun deletePlanetarySystemById(
        id: String,
        callback: PlanetarySystemRepository.PlanetarySystemListRepositoryCallback
    ) {
        VolleySingleton.getInstance().requestQueue

        val stringRequest = StringRequest(
            Request.Method.DELETE,
            ApiConfig.API_URL_BASE + "api/systems/${id}",
            Response.Listener {
                getPlanetarySystems(callback)
            },
            Response.ErrorListener {
                    error -> callback.onPlanetarySystemError(error.message)
            }
        )

        VolleySingleton.getInstance().addToRequestQueue(stringRequest)
    }

    override fun getPlanetsBySystemId(
        id: String,
        callback: PlanetarySystemRepository.PlanetsListRepositoryCallback
    ) {
        callback.onPlanetsLoading()
        VolleySingleton.getInstance().requestQueue

        val stringRequest = StringRequest(
            Request.Method.GET,
            ApiConfig.API_URL_BASE + "api/systems/${id}/planets",
            Response.Listener {
                    response ->
                var strResp = response.toString()
                val jsonObj = JSONArray(strResp)
                val list = mutableListOf<Planet>()
                for( i in 0 until jsonObj.length()) {
                    var obj = jsonObj.getJSONObject(i)
                    val resp = Gson().fromJson<Planet>(
                        obj.toString(), Planet::class.java
                    )
                    list.add(resp)

                }
                callback.onPLanetsResponse(list)
            },
            Response.ErrorListener {
                    error -> callback.onPlanetsError(error.message)
            }
        )
        VolleySingleton.getInstance().addToRequestQueue(stringRequest)
    }

    override fun deletePlanetById(
        systemId: String,
        id: String,
        callback: PlanetarySystemRepository.PlanetsListRepositoryCallback
    ) {
         VolleySingleton.getInstance().requestQueue

        val stringRequest = StringRequest(
            Request.Method.DELETE,
            ApiConfig.API_URL_BASE + "api/planets/${id}",
            Response.Listener {
                getPlanetsBySystemId(systemId, callback)
            },
            Response.ErrorListener {
                error -> callback.onPlanetsError(error.message)
            }
        )

        VolleySingleton.getInstance().addToRequestQueue(stringRequest)
    }

    override fun updatePlanet(
        id: String,
        planet: Planet,
        callback: PlanetarySystemRepository.RepositoryUpdateCallback
    ) {
        var jsonObj = JSONObject()
        jsonObj.put("_id", planet._id)
        jsonObj.put("name", planet.name)
        jsonObj.put("category", planet.category)
        jsonObj.put("orbitalPeriod", planet.orbitalPeriod)
        jsonObj.put("mass", planet.mass)
        jsonObj.put("equilibriumTemperature", planet.equilibriumTemperature)
        jsonObj.put("numberSatellites", planet.numberSatellites)
        jsonObj.put("gravity", planet.gravity)
        jsonObj.put("surface", planet.surface)
        jsonObj.put("planetImage", planet.planetImage)
        jsonObj.put("systemId", planet.systemId)

        VolleySingleton.getInstance().requestQueue

        val stringRequest = JsonObjectRequest(
            Request.Method.PUT,
            ApiConfig.API_URL_BASE + "api/planets/${id}",
            jsonObj,
            Response.Listener {
                    response ->
                callback.onPlanetarySystemResponse(response.toString())
            },
            Response.ErrorListener {
                    error ->
                callback.onPlanetarySystemError(error.message)
            }

        )

        VolleySingleton.getInstance().addToRequestQueue(stringRequest)    }

    override fun addPlanet(
        planet: Planet,
        callback: PlanetarySystemRepository.PlanetRepositoryCallback
    ) {
        var jsonObj = JSONObject()
        jsonObj.put("_id", planet._id)
        jsonObj.put("name", planet.name)
        jsonObj.put("category", planet.category)
        jsonObj.put("orbitalPeriod", planet.orbitalPeriod)
        jsonObj.put("mass", planet.mass)
        jsonObj.put("equilibriumTemperature", planet.equilibriumTemperature)
        jsonObj.put("numberSatellites", planet.numberSatellites)
        jsonObj.put("gravity", planet.gravity)
        jsonObj.put("surface", planet.surface)
        jsonObj.put("planetImage", planet.planetImage)
        jsonObj.put("systemId", planet.systemId)

        VolleySingleton.getInstance().requestQueue

        val stringRequest = JsonObjectRequest(
            Request.Method.POST,
            ApiConfig.API_URL_BASE + "api/planets",
            jsonObj,
            Response.Listener {
                    response ->
                callback.onPlanetResponse(parsePlanet(response.toString()))
            },
            Response.ErrorListener {
                    error ->
                callback.onPlanetError(error.message)
            }

        )

        VolleySingleton.getInstance().addToRequestQueue(stringRequest)
    }



    fun parseSystem(response: String?): PlanetarySystem {
        var system = PlanetarySystem("","","",0.0,"")
        try {
            val jsonObject = JSONObject(response)
            system._id = jsonObject.getString("_id")
            system.star = jsonObject.getString("star")
            system.constellation = jsonObject.getString("constellation")
            system.imageURL = jsonObject.getString("imageURL")
            system.distanceFromEarth = jsonObject.getDouble("distanceFromEarth")

        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return system
    }
    fun parsePlanet(response: String?): Planet{
        var planet = Planet("","","",0.0,"", 0,
            0,"","","","")
        try {
            val jsonObject = JSONObject(response)
            planet._id = jsonObject.getString("_id")
            planet.name = jsonObject.getString("name")
            planet.category = jsonObject.getString("category")
            planet.mass = jsonObject.getString("mass")
            planet.orbitalPeriod = jsonObject.getDouble("orbitalPeriod")
            planet.equilibriumTemperature = jsonObject.getInt("equilibriumTemperature")
            planet.numberSatellites = jsonObject.getInt("numberSatellites")
            planet.gravity = jsonObject.getString("gravity")
            planet.surface = jsonObject.getString("surface")
            planet.planetImage = jsonObject.getString("planetImage")
            planet.systemId = jsonObject.getString("systemId")

        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return planet
    }
}