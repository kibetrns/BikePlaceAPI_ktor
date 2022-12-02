package me.ipsum_amet.bikeplace.data.model

import kotlinx.serialization.Serializable

@Serializable
data class BikeDropOffAddress(
    val title: String? = null,
    val area: String? = null,
    val streetName: String? = null,
    val moreInfo: String? = null,
)
