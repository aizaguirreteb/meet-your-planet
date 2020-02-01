package com.iesvirgendelcarmen.meetyourplanet.model

data class PlanetarySystem (val id: Int,
                            val star: String,
                            val constellation: String,
                            val distanceFromEarth: Double,
                            val imageURL: String)