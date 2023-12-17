package record.video.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import core.components.KpFilterBar
import core.util.FilePaths
import record.home.presentation.component.KpSearchBar
import record.video.presentation.event.VideoEvent
import record.video.presentation.state.VideoState

@Composable
fun VideosSection(
    state: VideoState,
    onEvent: (VideoEvent) -> Unit
) {
    LaunchedEffect(Unit){
        onEvent(VideoEvent.GetVideosByPath(FilePaths.VideosPath))
    }

    var searchedVideos by remember {
        mutableStateOf(state.videos)
    }

    var searchQuery by remember {
        mutableStateOf("")
    }

    Row (
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
    ) {
        KpSearchBar(
            modifier = Modifier.height(200.dp),
            searchQuery = searchQuery,
            searchResults = searchedVideos,
            onSearchQueryChange = { query ->
                searchQuery = query
                searchedVideos = state.videos.filter { video ->
                    video.name.contains(query, ignoreCase = true)
                }
            }
        )
        KpFilterBar(
            modifier = Modifier.height(200.dp),
            filterOptions = listOf("All", "Videos", "Audios", "Images"),
            filterResults = searchedVideos,
            onFilter = { },
            onFilterQueryChange = { }
        )
    }

    LazyColumn(
        modifier = Modifier,
        horizontalAlignment = Alignment.Start
    ) {
        searchedVideos.forEach { item ->
            item{
                Text(
                    text = item.name,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier,
                )
            }
        }
    }
}
