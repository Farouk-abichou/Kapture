
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
                    screenRecorder.recordScreen(
                        config = ConfigurationManager(),
                        bounds = null
                    )
                }
            ) {
                Text("Record")
            }
            Button(
                onClick = {
                    val bounds = WindowBounds(x1 = 100, y1 = 100, x2 = 500, y2 = 400)
                    screenRecorder.recordScreen(bounds = bounds, config = ConfigurationManager())
                }
            ) {
                Text("Record Section")
            }
        }
    }
}
