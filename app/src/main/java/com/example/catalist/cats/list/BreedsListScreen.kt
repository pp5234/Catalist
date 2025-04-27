package com.example.catalist.cats.list

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import com.example.catalist.cats.domain.BreedsListData
import com.example.catalist.core.LoadingIndicator
import com.example.catalist.core.NoDataContent
import com.example.catalist.core.TextToChips


@Composable
fun BreedsListScreen(
    viewModel : BreedsListViewModel,
    onBreedListClick: (id : String) -> Unit
) {
    val uiState = viewModel.state.collectAsState()
    BreedsListScreen(uiState.value, onBreedListClick)
}

@Composable
private fun BreedsListScreen(
    state : BreedsListScreenContract.UiState,
    onBreedListClick: (id: String) -> Unit
) {
    Scaffold (
        topBar = {
            SearchBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                onSearch = {string -> Log.d("String", string)})
        }
    ) { padding ->
        if (state.loading)
            LoadingIndicator()
        else if (state.error != null)
            NoDataContent(modifier = Modifier.padding(padding), text = "Error while fetching data:\n${state.error.message}")
        else if (state.data.isEmpty())
            NoDataContent(modifier = Modifier.padding(padding), text = "No breeds to list")
        else {
            BreedsListColumn (
                modifier = Modifier
                    .padding(padding)
                    .background(MaterialTheme.colorScheme.background),
                data = state.data,
                onBreedListClick = onBreedListClick
            )
        }
    }
}

@Composable
private fun BreedsListColumn(
    modifier : Modifier,
    data: List<BreedsListData>,
    onBreedListClick: (id: String) -> Unit
) {
    LazyColumn (
        modifier = modifier
    ) {
        items(data) { breed ->
            BreedsListItem(
                data = breed,
                onClick = {onBreedListClick(breed.id)}
            )
        }
    }
}

@Composable
private fun BreedsListItem (
    data: BreedsListData,
    onClick: () -> Unit
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 16.dp),
        onClick = onClick,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondary)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = buildString {
                    append(data.name)
                        if (data.alt.isNotEmpty()) {
                            append(" (${data.alt})")
                        }
                    },
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))


            Text(
                modifier = Modifier.fillMaxWidth(),
                text = data.description,
                style = MaterialTheme.typography.bodySmall,
            )

            Spacer(modifier = Modifier.height(8.dp))


            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {

                TextToChips(text = data.temperament, amount = 5, delim = ',')

            }

        }
    }
}

@Composable
fun SearchBar(modifier: Modifier = Modifier,
    onSearch: (String) -> Unit
) {
    var query by rememberSaveable { mutableStateOf("") }
    TextField(
        value = query,
        onValueChange = { newValue -> query = newValue},
        placeholder = { Text("Search") },
        leadingIcon = { IconButton (onClick = { onSearch(query) }) {
            Icon(Icons.Default.Search, contentDescription = null)
          }},
        trailingIcon = {
            if (query.isNotEmpty()) {
                IconButton(onClick = { query = "" }) {
                    Icon(Icons.Default.Close, contentDescription = "Clear")
                }
            }
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(onSearch = {onSearch(query); query = ""}),

        modifier = modifier
    )
}





