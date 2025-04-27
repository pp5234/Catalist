package com.example.catalist.cats.details

import com.example.catalist.cats.details.model.BreedsDetailsUiModel

interface BreedsDetailsScreenContract {
    data class UIState (
        val loading: Boolean = true,
        val data: BreedsDetailsUiModel? = null,
        val error: Throwable? = null
    )
}