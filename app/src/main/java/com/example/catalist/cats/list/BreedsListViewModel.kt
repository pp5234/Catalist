package com.example.catalist.cats.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catalist.cats.domain.BreedsListData
import com.example.catalist.cats.domain.IBreedsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BreedsListViewModel @Inject constructor(
    private val breedsRepository: IBreedsRepository
) : ViewModel() {

    val _state = MutableStateFlow(BreedsListScreenContract.UiState())
    val state = _state.asStateFlow()
    private fun setState(reducer: BreedsListScreenContract.UiState.() -> BreedsListScreenContract.UiState) = _state.getAndUpdate(reducer)

    init {
        loadBreedsList()
    }

    private fun loadBreedsList() = viewModelScope.launch {
        try {
            val data = breedsRepository.fetchAllBreeds()
            setState { copy (loading = false, data = data ?: emptyList()) }
        } catch (e : Exception) {
            setState { copy (loading = false, error = e) }
        }

    }
}