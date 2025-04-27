package com.example.catalist.cats.details

import com.example.catalist.cats.domain.BreedsDetailsData

interface BreedsDetailsScreenContract {
    data class UIState (
        val loading: Boolean = true,
        val data: BreedsDetailsData? = null,
        val error: Throwable? = null
    )
}