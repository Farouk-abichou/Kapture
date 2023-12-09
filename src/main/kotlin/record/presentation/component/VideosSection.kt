package record.presentation.component

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import record.presentation.event.RecordEvent
import record.presentation.state.RecordState

@Composable
fun VideosSection(
    state: RecordState,
    onEvent: (RecordEvent) -> Unit
) {
    LazyColumn {
        item {

        }
    }
}
