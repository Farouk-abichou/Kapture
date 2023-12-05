import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import home.presentation.HomeScreen
import home.presentation.HomeViewModel

@Composable
@Preview
fun App() {
    val viewModel = HomeViewModel()
    MaterialTheme {
        HomeScreen(
            state = viewModel.state.value,
            onEvent = viewModel::onEvent
        )
    }
}