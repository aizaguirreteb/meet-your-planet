package com.iesvirgendelcarmen.meetyourplanet.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Planet (val _id: String,
                   val name: String,
                   val category: String,
                   val orbitalPeriod: Double,
                   val mass: String,
                   val equilibriumTemperature: Int,
                   val numberSatellites: Int,
                   val gravity: String,
                   val surface: String,
                   val planetImage: String,
                   val systemId: String) : Parcelable
