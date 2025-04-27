import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import coil3.compose.SubcomposeAsyncImageContent
import com.example.catalist.cats.details.BreedsDetailsScreenContract
import com.example.catalist.cats.details.BreedsDetailsViewModel
import com.example.catalist.cats.domain.BreedsDetailsData
import com.example.catalist.core.LoadingIndicator
import com.example.catalist.core.NoDataContent
import com.example.catalist.core.TextToChips
import androidx.core.net.toUri


@Composable
fun BreedsDetailsScreen (viewModel: BreedsDetailsViewModel) {
    val uiState = viewModel.state.collectAsState()
    BreedsDetailsScreen(uiState.value)
}

@Composable
private fun BreedsDetailsScreen(state: BreedsDetailsScreenContract.UIState) {
    if (state.loading)
        LoadingIndicator()
    else if (state.error != null)
        NoDataContent("Error when fetching details: ${state.error}")
    else if (state.data == null)
        NoDataContent("No details about this breed")
    else
        BreedsDetailsScreenContent(breed = state.data)
}

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
private fun BreedsDetailsScreenContent(
    breed: BreedsDetailsData
) {
    val scrollState = rememberScrollState()
    val borderModifier =
        if (breed.isRare) {
            Modifier.border(
                width = 8.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(8.dp)
            )
        } else Modifier

    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //Heading
        Text(
            text = breed.name,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 8.dp),
            textAlign = TextAlign.Center
        )
        // Image
        BreedImage(
            modifier = Modifier
                .size(250.dp)
                .then(borderModifier)
                .padding(16.dp),
            url = breed.imageUrl,
            description = "${breed.name} Image",
        )
        //Info
        BreedInfo(
            modifier = Modifier
                .width(300.dp)
                .padding(bottom = 16.dp, top = 4.dp),
            breed = breed
        )
        HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))

        // Description
        Text(
            text = "Description",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        Text(
            text = breed.description,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
        // Temperament
        Text(
            text = "Temperament",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        FlowRow(
            modifier = Modifier.padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {

            TextToChips(
                modifier = Modifier
                    .padding(2.dp),
                text = breed.temperament,
                delim = ','
            )

        }
        HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
        //BehavioralTraits
        BreedBehavioralTraits(
            textModifier = Modifier.padding(bottom = 8.dp),
            indicatorModifier = Modifier.width(300.dp),
            breed = breed
        )
        Spacer(modifier = Modifier.height(16.dp))
        // Wiki
        if (breed.wikipediaUrl.isNotEmpty())
            WikiButton(breed.wikipediaUrl)

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
private fun ColumnScope.WikiButton(wikipediaUrl: String) {
    val context = LocalContext.current
        Button(
            onClick = {
                val intent = Intent(Intent.ACTION_VIEW, wikipediaUrl.toUri())
                context.startActivity(intent)
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("View on Wikipedia")
        }
}

@Composable
private fun BreedInfo(modifier: Modifier, breed: BreedsDetailsData) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        BreedInfoColumn(title = "Origin", value = breed.origin)
        BreedInfoColumn(title = "Life Span", value = "${breed.lifeSpan} years")
        BreedInfoColumn(title = "Weight", value = "${breed.weightMetric} kg")
    }
}


@Composable
private fun BreedImage(modifier : Modifier = Modifier, url : String?, description: String) {
    if (url != null) {
        SubcomposeAsyncImage(
            model = url,
            contentDescription = description,
            modifier = modifier,
            loading = {
                LoadingIndicator()
            },
            error = {
                Box(
                    modifier = modifier,
                    contentAlignment = Alignment.Center
                ) {
                    Text("Failed to fetch image", style = MaterialTheme.typography.bodyMedium)
                }
            },
            success = {
                SubcomposeAsyncImageContent()
            }
        )
    } else {
        Box(
            modifier = modifier,
            contentAlignment = Alignment.Center
        ) {
            Text("No Image Available", style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable
private fun BreedInfoColumn(title: String, value: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = title, style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(2.dp))
        Text(text = value, style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable
fun BreedBehaviorTraitIndicator(
    label: String,
    level: Int,
    maxLevel: Int = 5
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp)
        )

        LinearProgressIndicator(
            progress = { level / maxLevel.toFloat() },
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun BreedBehavioralTraits(
    breed: BreedsDetailsData,
    textModifier : Modifier = Modifier,
    indicatorModifier : Modifier = Modifier
) {
    Text(
        text = "Behavioral Traits",
        style = MaterialTheme.typography.titleMedium,
        modifier = textModifier
    )
    Column(modifier = indicatorModifier) {
        BreedBehaviorTraitIndicator(label = "Adaptability", level = breed.adaptability)
        BreedBehaviorTraitIndicator(label = "Intelligence", level = breed.intelligence)
        BreedBehaviorTraitIndicator(label = "Vocalisation", level = breed.vocalisation)
        BreedBehaviorTraitIndicator(label = "Health Issues", level = breed.healthIssues)
        BreedBehaviorTraitIndicator(label = "Grooming Needs", level = breed.grooming)
        BreedBehaviorTraitIndicator(label = "Social Needs", level = breed.socialNeeds)
        BreedBehaviorTraitIndicator(label = "Affection Level", level = breed.affectionLevel)
        BreedBehaviorTraitIndicator(label = "Energy Level", level = breed.energyLevel)
        BreedBehaviorTraitIndicator(label = "Shedding Level", level = breed.sheddingLevel)
        BreedBehaviorTraitIndicator(label = "Child Friendly", level = breed.childFriendly)
        BreedBehaviorTraitIndicator(label = "Dog Friendly", level = breed.dogFriendly)
        BreedBehaviorTraitIndicator(label = "Stranger Friendly", level = breed.strangerFriendly)
    }
}
