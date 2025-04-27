package com.example.catalist.cats.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catalist.cats.domain.IBreedsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BreedsDetailsViewModel @Inject constructor(
    savedState : SavedStateHandle,
    private val breedsRepository: IBreedsRepository
) : ViewModel() {

    val breedId = savedState.get<String>("breedId") ?: error("BreedId not found")

    val _state = MutableStateFlow(BreedsDetailsScreenContract.UIState())
    val state = _state.asStateFlow()
    private fun setState(reducer: BreedsDetailsScreenContract.UIState.() -> BreedsDetailsScreenContract.UIState) = _state.getAndUpdate(reducer)

    init {
        loadBreedDetails()
    }

    private fun loadBreedDetails() = viewModelScope.launch {
        try {
            val data = breedsRepository.fetchBreedById(breedId)
            setState { copy(loading = false, data = data)}
        } catch (e : Exception) {
            setState { copy(loading = false, error = e)}
        }


    }
}