package com.iesvirgendelcarmen.meetyourplanet.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Planet (var _id: String,
                   var name: String,
                   var category: String,
                   var orbitalPeriod: Double,
                   var mass: String,
                   var equilibriumTemperature: Int,
                   var numberSatellites: Int,
                   var gravity: String,
                   var surface: String,
                   var planetImage: String,
                   var systemId: String) : Parcelable
