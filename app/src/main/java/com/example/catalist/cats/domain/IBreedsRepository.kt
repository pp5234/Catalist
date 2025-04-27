package com.example.catalist.cats.domain

import com.example.catalist.cats.details.model.BreedsDetailsUiModel
import com.example.catalist.cats.list.model.BreedsListUiModel

interface IBreedsRepository {
    suspend fun fetchAllBreeds() : List<BreedsListUiModel>?
    suspend fun fetchBreedsByQuery(query : String) : List<BreedsListUiModel>?
    suspend fun fetchBreedById(breedId : String) : BreedsDetailsUiModel?
}