package com.example.catalist.cats.repository

import com.example.catalist.cats.api.BreedsApi
import com.example.catalist.cats.api.model.BreedsApiModel
import com.example.catalist.cats.api.model.ImageApiModel
import com.example.catalist.cats.domain.IBreedsRepository
import javax.inject.Inject

class BreedsRepository @Inject constructor(
    private val breedsApi: BreedsApi

) : IBreedsRepository {
    override suspend fun fetchAllBreeds(): List<BreedsApiModel> {
        val data = breedsApi.getAllBreeds()
        return data
    }

    override suspend fun fetchBreedById(breedId: String): BreedsApiModel {
       return breedsApi.getBreedById(breedId)
    }

    override suspend fun fetchBreedsByQuery(query: String): List<BreedsApiModel> {
        return breedsApi.getBreedsByQuery(query)
    }

    override suspend fun fetchImageById(imageId: String): ImageApiModel {
        return try {
            breedsApi.getImageById(imageId)
        } catch (e : Exception) {
            ImageApiModel()
        }
    }

}