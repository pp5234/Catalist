package com.example.catalist.cats.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImageApiModel (
    val id : String? = "",
    @SerialName("url")
    val imageUrl : String? = "",
)