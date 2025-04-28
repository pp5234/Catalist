package com.example.catalist.cats.domain

import com.example.catalist.cats.api.model.BreedsApiModel
import com.example.catalist.cats.api.model.ImageApiModel

interface IBreedsRepository {
    suspend fun fetchAllBreeds() : List<BreedsApiModel>
    suspend fun fetchBreedsByQuery(query : String) : List<BreedsApiModel>
    suspend fun fetchBreedById(breedId : String) : BreedsApiModel
    suspend fun fetchImageById(imageId : String) : ImageApiModel
}