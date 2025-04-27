package com.example.catalist.cats.repository

import com.example.catalist.cats.details.model.BreedsDetailsUiModel
import com.example.catalist.cats.domain.IBreedsRepository
import com.example.catalist.cats.domain.dummyBreedDetailsMap
import com.example.catalist.cats.domain.dummyBreedList
import com.example.catalist.cats.list.model.BreedsListUiModel
import kotlinx.coroutines.delay
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

class BreedsRepository @Inject constructor() : IBreedsRepository {
    override suspend fun fetchAllBreeds(): List<BreedsListUiModel> {
        delay(2.seconds)
        return dummyBreedList
    }

    override suspend fun fetchBreedById(breedId: String): BreedsDetailsUiModel? {
        delay(2.seconds)
        return dummyBreedDetailsMap[breedId]
    }

    override suspend fun fetchBreedsByQuery(query: String): List<BreedsListUiModel> {
        TODO("Not yet implemented")
    }
}