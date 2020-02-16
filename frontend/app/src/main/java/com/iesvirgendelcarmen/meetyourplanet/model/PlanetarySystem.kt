package com.iesvirgendelcarmen.meetyourplanet.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PlanetarySystem (val _id: String,
                            val star: String,
                            val constellation: String,
                            val distanceFromEarth: Double,
                            val imageURL: String) : Parcelable