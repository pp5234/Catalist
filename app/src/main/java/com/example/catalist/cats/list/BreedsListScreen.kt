package com.example.catalist.cats.list

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
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.example.catalist.cats.domain.BreedsListData

@Composable
fun BreedsListScreen(
    data : List<BreedsListData>,
    onBreedListClick: (id: String) -> Unit

) {
    Scaffold { padding ->
        BreedsListColumn (
            modifier = Modifier
                .padding(padding)
                .background(MaterialTheme.colorScheme.background),
            data = data,
            onBreedListClick = onBreedListClick
        )
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
            // Centered Heading
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

                TemperamentChips(data.temperament, onClick)

            }

        }
    }
}


@Composable
fun TemperamentChips(data : List<String>, onClick: () -> Unit) {
    val colors = AssistChipDefaults.assistChipColors(labelColor = Color.White, containerColor = MaterialTheme.colorScheme.primary)
    for (text in data.take(5)) {
        AssistChip(colors = colors , label = {Text(text = text, style = MaterialTheme.typography.labelSmall)}, onClick = onClick)
        }


}