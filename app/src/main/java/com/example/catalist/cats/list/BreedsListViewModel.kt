package com.example.catalist.cats.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catalist.cats.domain.BreedsListData
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

class BreedsListViewModel @Inject constructor(
) : ViewModel() {

    val _state = MutableStateFlow(BreedsListScreenContract.UiState())
    val state = _state.asStateFlow()
    private fun setState(reducer: BreedsListScreenContract.UiState.() -> BreedsListScreenContract.UiState) = _state.getAndUpdate(reducer)

    init {
        loadBreedsList()
    }

    private fun loadBreedsList() = viewModelScope.launch {
        delay(2.seconds)
        setState { copy (loading = false, data = listOf(BreedsListData(),BreedsListData(),BreedsListData(),BreedsListData())) }
    }
}