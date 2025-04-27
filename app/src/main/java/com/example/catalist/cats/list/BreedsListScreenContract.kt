package com.example.catalist.cats.list

import com.example.catalist.cats.list.model.BreedsListUiModel

interface BreedsListScreenContract {

    data class UiState (
        val loading : Boolean = true,
        val isSearched : Boolean = false,
        val data : List<BreedsListUiModel> = emptyList(),
        val error: Throwable? = null,
    )
}