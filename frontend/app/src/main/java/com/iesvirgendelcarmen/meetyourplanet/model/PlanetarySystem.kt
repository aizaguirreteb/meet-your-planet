package com.iesvirgendelcarmen.meetyourplanet.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PlanetarySystem (var _id: String,
                            var star: String,
                            var constellation: String,
                            var distanceFromEarth: Double,
                            var imageURL: String) : Parcelable