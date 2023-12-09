package record.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import record.presentation.component.RecordContent
import record.presentation.event.RecordEvent
import record.presentation.state.RecordState

@Composable
fun RecordScreen(
    state: RecordState,
    onEvent: (RecordEvent) -> Unit,
) {
    Scaffold(
        topBar = { },
        bottomBar = { },
        floatingActionButton = { },
        containerColor = MaterialTheme.colorScheme.background,
        modifier = Modifier
            .fillMaxSize(),
    ) { paddingValues ->
        if (state.isLoading) {
            Text("Loading...")
        } else {
            RecordContent(
                state = state,
                onEvent = onEvent
            )
        }
    }
}