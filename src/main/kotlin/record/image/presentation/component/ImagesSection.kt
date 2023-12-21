package record.image.presentation.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import core.components.KpFilterDropdown
import core.components.KpSortDropdown
import record.home.presentation.component.KpSearchBar
import record.image.presentation.event.ImageEvent
import record.image.presentation.state.ImageState

@Composable
fun ImagesSection(
    modifier: Modifier,
    state: ImageState,
    onEvent: (ImageEvent) -> Unit,
) {
    var searchedImages by remember { mutableStateOf(state.images) }

    val filterOptions = listOf("All", "Movies", "TV Shows")
    val sortOptions = listOf("Name", "Date Added", "Size")

    var searchQuery by remember {
        mutableStateOf("")
    }

    Column(
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(8.dp)
        ) {
            KpSearchBar(
                modifier = Modifier.width(200.dp),
                searchQuery = searchQuery,
                onSearchQueryChange = { query ->
                    searchQuery = query
                    searchedImages = state.images.filter { video ->
                        video.name.contains(query, ignoreCase = true)
                    }
                }
            )
            KpFilterDropdown(
                modifier = Modifier,
                filterOptions = filterOptions,
                onFilter = {
                    searchedImages = if (it == "All") state.images else state.images.filter { video ->
                        video.name.contains(it, ignoreCase = true)
                    }
                }
            )
            KpSortDropdown(
                modifier = Modifier,
                sortOptions = sortOptions,
                onSort = {
                    searchedImages = when (it) {
                        "Name" -> searchedImages.sortedBy { audio -> audio.name }
                        "Date Added" -> searchedImages.sortedBy { audio -> audio.dateCreated }
                        "Size" -> searchedImages.sortedBy { audio -> audio.size }

                        else -> emptyList()
                    }
                }
            )

        }

        LazyColumn(
            modifier = Modifier,
            horizontalAlignment = Alignment.Start
        ) {
            searchedImages.forEach { item ->
                item {
                    KpImageItem(
                        modifier = Modifier,
                        image = item,
                        onClick = {}
                    )
                }
            }
        }
    }
}