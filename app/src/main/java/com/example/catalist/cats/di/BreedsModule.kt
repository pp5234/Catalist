package com.example.catalist.cats.di

import com.example.catalist.cats.domain.IBreedsRepository
import com.example.catalist.cats.repository.BreedsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BreedsModule {

    @Module
    @InstallIn(SingletonComponent::class)
    abstract class RepositoryModule {
        @Binds @Singleton
        abstract fun bindBreedsRepository(
            impl: BreedsRepository
        ): IBreedsRepository
    }


}