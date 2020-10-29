package com.murano500k.dogbreeds.model


import com.google.gson.annotations.SerializedName

data class ResponseMultipleImages(
    @SerializedName("message")
    val message: List<String> = listOf(),
    @SerializedName("status")
    val status: String = ""
)