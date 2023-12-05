import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import record.data.RecorderRepositoryImpl

fun main() = application {

    Window(
        onCloseRequest = ::exitApplication,
        title = "Screen Recorder"
    ) {
        App()
    }
}