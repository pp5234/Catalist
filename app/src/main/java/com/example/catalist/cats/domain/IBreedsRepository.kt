package com.example.catalist.cats.domain

interface IBreedsRepository {
    suspend fun fetchAllBreeds() : List<BreedsListData>?
    suspend fun fetchBreedsByQuery(query : String) : List<BreedsListData>?
    suspend fun fetchBreedById(breedId : String) : BreedsDetailsData?
}