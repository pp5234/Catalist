package com.example.catalist.cats.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catalist.cats.api.mapper.asBreedsDetailsData
import com.example.catalist.cats.api.model.BreedsApiModel
import com.example.catalist.cats.api.model.ImageApiModel
import com.example.catalist.cats.domain.IBreedsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okio.IOException
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
        setState { copy(loading = true)}
        try {
            val breedRes : BreedsApiModel
            val imageRes : ImageApiModel
            withContext(Dispatchers.IO) {
                breedRes = breedsRepository.fetchBreedById(breedId)
                imageRes = breedsRepository.fetchImageById(breedRes.imageId)
            }
            val breed = asBreedsDetailsData(breedRes, imageRes.imageUrl)
            setState { copy(loading = false, data = breed)}
        } catch (e : IOException) {
            setState { copy(loading = false, error = e)}
        }
    }
}