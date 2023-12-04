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
            Button(onClick = {
                println(getTerminalWindowBounds())
                screenRecorder.startRecording(
                    x = getTerminalWindowBounds()!![0],
                    y = getTerminalWindowBounds()!![1],
                    width = getTerminalWindowBounds()!![2],
                    height = getTerminalWindowBounds()!![3]
                )
            }) {
                Text("Record")
            }
            Button(onClick = {
                screenRecorder.stopRecording()
            }) {
                Text("Stop")
            }
        }
    }
}
