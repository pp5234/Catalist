package com.example.catalist.cats.list

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catalist.cats.api.mapper.asBreedsListUiModel
import com.example.catalist.cats.domain.IBreedsRepository
import com.example.catalist.cats.list.model.BreedsListUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class BreedsListViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val breedsRepository: IBreedsRepository
) : ViewModel() {

    val query = savedStateHandle.get<String>("q") ?: ""
    val _state = MutableStateFlow(BreedsListScreenContract.UiState())
    val state = _state.asStateFlow()
    private fun setState(reducer: BreedsListScreenContract.UiState.() -> BreedsListScreenContract.UiState) = _state.getAndUpdate(reducer)

    init {
        loadBreedsList()
    }

    private fun loadBreedsList() = viewModelScope.launch {
        setState { copy (loading = true)}
        try {
            val data : List<BreedsListUiModel>
            Log.d("BreedsListViewModel", query)
            withContext(Dispatchers.IO) {
                data = if (query == "")
                    breedsRepository.fetchAllBreeds().map { asBreedsListUiModel(it) }
                else
                    breedsRepository.fetchBreedsByQuery(query).map { asBreedsListUiModel(it) }
            }
            setState { copy (loading = false, data = data) }
        } catch (e : Exception) {
            Log.d("BreedsListViewModel: ", e.message.toString())
            setState { copy (loading = false, error = e) }
        }

    }

}