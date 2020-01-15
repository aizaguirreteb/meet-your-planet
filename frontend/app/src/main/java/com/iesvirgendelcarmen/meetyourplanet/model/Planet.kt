package com.iesvirgendelcarmen.meetyourplanet.model

data class Planet (val name: String,
                   val category: String,
                   val orbitalPeriod: Double,
                   val mass: Double,
                   val equilibriumTemperature: Int,
                   val numberSatellites: Int,
                   val gravity: Double)
