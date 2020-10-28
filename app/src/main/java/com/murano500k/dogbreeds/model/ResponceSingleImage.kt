package com.murano500k.dogbreeds.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponceSingleImage(
    @Json(name = "message")
    val message: String = "",
    @Json(name = "status")
    val status: String = ""
)