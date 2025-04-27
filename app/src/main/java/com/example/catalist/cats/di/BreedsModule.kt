package com.example.catalist.cats.di

import com.example.catalist.cats.domain.IBreedsRepository
import com.example.catalist.cats.repository.BreedsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BreedsModule {

    @Provides
    @Singleton
    fun bindsBreedsRepository(): IBreedsRepository = BreedsRepository()

}