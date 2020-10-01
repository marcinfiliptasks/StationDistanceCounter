package com.mf.domain.models.station

interface IStation {
    val id: Long
    val name: String
    val nameSlug: String
    val latitude: Double
    val longitude: Double
    val hits:Long
    val ibnr: String?
    val city:String
    val region: String
    val country: String
    val localisedName:String?
}