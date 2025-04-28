package com.example.catalist.cats.api.mapper

import com.example.catalist.cats.api.model.BreedsApiModel
import com.example.catalist.cats.details.model.BreedsDetailsUiModel
import com.example.catalist.cats.list.model.BreedsListUiModel

fun asBreedsDetailsData(data : BreedsApiModel, imageUrl: String?) : BreedsDetailsUiModel {
    return BreedsDetailsUiModel(
        name = data.name ?: "",
        description = data.description ?: "",
        origin = data.origin ?: "",
        temperament = data.temperament ?: "",
        lifeSpan = data.lifeSpan ?: "",
        weightMetric = data.weight?.metric ?: "",
        adaptability = data.adaptability ?: 0,
        affectionLevel = data.affectionLevel ?: 0,
        childFriendly = data.childFriendly ?: 0,
        dogFriendly = data.dogFriendly ?: 0,
        energyLevel = data.energyLevel ?: 0,
        grooming = data.grooming ?: 0,
        healthIssues = data.healthIssues ?: 0,
        intelligence = data.intelligence ?: 0,
        sheddingLevel = data.sheddingLevel ?: 0,
        socialNeeds = data.socialNeeds ?: 0,
        strangerFriendly = data.strangerFriendly ?: 0,
        vocalisation = data.vocalisation ?: 0,
        isRare = data.isRare ?: 0,
        wikipediaUrl = data.wikipediaUrl ?: "",
        imageUrl = imageUrl ?: ""
    )
}

fun asBreedsListUiModel(data : BreedsApiModel) : BreedsListUiModel {
    return BreedsListUiModel (
        id = data.id,
        name = data.name ?: "",
        alt = data.alt ?: "",
        description = data.description ?: "",
        temperament =  data.temperament ?: ""
    )
}