import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
@Preview
fun App(screenRecorder: ScreenRecorder) {
    MaterialTheme {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Button(
                onClick = {
                    val bounds = getWindowBounds("Terminal")
                    if (bounds != null) {
                        println("Bounds: ${bounds.x1}, ${bounds.y1}, ${bounds.x2}, ${bounds.y2}")
                        println("Width: ${bounds.width}, Height: ${bounds.height}")
                        screenRecorder.startRecording(bounds)
                    }
                }
            ) {
                Text("Record")
            }
            Button(
                onClick = {
                    screenRecorder.stopRecording()
                }
            ) {
                Text("Stop")
            }
        }
    }
}
