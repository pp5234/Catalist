package com.example.catalist.cats.details


import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catalist.cats.domain.BreedsDetailsData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

@HiltViewModel
class BreedsDetailsViewModel @Inject constructor(
    savedState : SavedStateHandle
) : ViewModel() {

    val breedId = savedState.get<String>("breedId") ?: error("BreedId not found")

    val _state = MutableStateFlow(BreedsDetailsScreenContract.UIState(breedId = breedId))
    val state = _state.asStateFlow()
    private fun setState(reducer: BreedsDetailsScreenContract.UIState.() -> BreedsDetailsScreenContract.UIState) = _state.getAndUpdate(reducer)

    init {
        loadBreedDetails()
    }

    private fun loadBreedDetails() = viewModelScope.launch {
        delay(2.seconds)
        setState { copy(breedId = breedId, loading = false, data = BreedsDetailsData()) }
    }
}