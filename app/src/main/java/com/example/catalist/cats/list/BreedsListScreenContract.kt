package com.example.catalist.cats.list

import com.example.catalist.cats.domain.BreedsListData

interface BreedsListScreenContract {

    data class UiState (
        val loading : Boolean = true,
        val isSearched : Boolean = false,
        val data : List<BreedsListData> = emptyList(),
        val error: Throwable? = null,
    )
}