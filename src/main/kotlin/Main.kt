import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import home.data.RecorderRepositoryImpl

fun main() = application {
    val recorderRepositoryImpl = RecorderRepositoryImpl()
    Window(
        onCloseRequest = ::exitApplication,
        title = "Screen Recorder"
    ) {
        App(recorderRepositoryImpl)
    }
}