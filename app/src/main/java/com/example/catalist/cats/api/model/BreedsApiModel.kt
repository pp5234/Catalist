package com.example.catalist.cats.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Weight(
    val imperial: String,
    val metric: String
)

@Serializable
data class BreedsApiModel(
    val id : String,
    val name: String? = "",
    @SerialName("alt_names")
    val alt: String? = "",
    val temperament: String? = "",
    val description: String? = "",
    val origin: String? = "",
    @SerialName("life_span")
    val lifeSpan: String? = "",
    val adaptability: Int? = 0,
    @SerialName("affection_level")
    val affectionLevel: Int? = 0,
    @SerialName("child_friendly")
    val childFriendly: Int? = 0,
    @SerialName("dog_friendly")
    val dogFriendly: Int? = 0,
    @SerialName("energy_level")
    val energyLevel: Int? = 0,
    val grooming: Int? = 0,
    @SerialName("health_issues")
    val healthIssues: Int? = 0,
    val intelligence: Int? = 0,
    @SerialName("shedding_level")
    val sheddingLevel: Int? = 0,
    @SerialName("social_needs")
    val socialNeeds: Int? = 0,
    @SerialName("stranger_friendly")
    val strangerFriendly: Int? = 0,
    val vocalisation: Int? = 0,
    @SerialName("rare")
    val isRare: Int? = 0,
    @SerialName("wikipedia_url")
    val wikipediaUrl: String? = "",
    @SerialName("reference_image_id")
    val imageId: String = "",
    val weight: Weight? = null,
)