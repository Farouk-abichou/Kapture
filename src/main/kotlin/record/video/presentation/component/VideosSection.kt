package record.video.presentation.component

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import core.util.FilePaths
import record.video.domain.model.Video
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

    LazyColumn {
        state.videos.forEach { video: Video ->
            item{
                Text(
                    text = video.name,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier,
                )
            }
        }
    }
}
