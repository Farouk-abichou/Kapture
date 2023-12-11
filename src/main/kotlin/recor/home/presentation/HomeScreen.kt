package recor.home.presentation

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import recor.home.presentation.component.HomeContent
import recor.home.presentation.event.HomeEvent
import recor.home.presentation.state.HomeState

@Composable
fun HomeScreen(
    state: HomeState,
    onEvent: (HomeEvent) -> Unit = {},
) {
    if (state.isLoading) {
        Text("Loading...")
    } else {
        HomeContent(
            state = state,
            onEvent = onEvent
        )
    }
}