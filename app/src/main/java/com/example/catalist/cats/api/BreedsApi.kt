package com.example.catalist.cats.api

import com.example.catalist.cats.api.model.BreedsApiModel
import com.example.catalist.cats.api.model.ImageApiModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BreedsApi {
    @GET("breeds")
    suspend fun getAllBreeds(): List<BreedsApiModel>

    @GET("breeds/{id}")
    suspend fun getBreedById(
        @Path("id") breedId: String,
    ): BreedsApiModel

    @GET("breeds/search")
    suspend fun getBreedsByQuery(
        @Query("q") query : String
    ) : List<BreedsApiModel>

    @GET("images/{id}")
    suspend fun getImageById(
        @Path("id") imageId : String
    ) : ImageApiModel
}