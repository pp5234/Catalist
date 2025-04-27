package com.example.catalist.cats.repository

import com.example.catalist.cats.domain.BreedsDetailsData
import com.example.catalist.cats.domain.BreedsListData
import com.example.catalist.cats.domain.IBreedsRepository
import com.example.catalist.cats.domain.dummyBreedDetailsMap
import com.example.catalist.cats.domain.dummyBreedList
import kotlinx.coroutines.delay
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

class BreedsRepository @Inject constructor() : IBreedsRepository {
    override suspend fun fetchAllBreeds(): List<BreedsListData> {
        delay(2.seconds)
        return dummyBreedList
    }

    override suspend fun fetchBreedById(breedId: String): BreedsDetailsData? {
        delay(2.seconds)
        return dummyBreedDetailsMap[breedId]
    }

    override suspend fun fetchBreedsByQuery(query: String): List<BreedsListData> {
        TODO("Not yet implemented")
    }
}