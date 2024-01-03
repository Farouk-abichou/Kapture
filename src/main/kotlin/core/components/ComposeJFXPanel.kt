package core.components

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.ComposeWindow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.round
import javafx.application.Platform
import javafx.embed.swing.JFXPanel
import java.awt.BorderLayout
import javax.swing.JPanel

@Composable
fun ComposeJFXPanel(
    modifier: Modifier,
    composeWindow: ComposeWindow,
    jfxPanel: JFXPanel,
    onCreate: () -> Unit,
    onDestroy: () -> Unit = {}
) {
    val jPanel = remember { JPanel() }
    val density = LocalDensity.current.density

    Box(
        modifier = Modifier.onGloballyPositioned { childCoordinates ->
            val coordinates = childCoordinates.parentCoordinates!!
            val location = coordinates.localToWindow(Offset.Zero).round()
            jPanel.setBounds(
                (location.x / density).toInt(),
                (location.y / density).toInt(),
                (coordinates.size.width / density).toInt(),
                (coordinates.size.height / density).toInt()
            )
        }
    )

    DisposableEffect(jPanel) {
        composeWindow.add(jPanel)
        jPanel.layout = BorderLayout()
        jPanel.add(jfxPanel)

        Platform.runLater {
            onCreate()
        }
        onDispose {
            onDestroy()
            composeWindow.remove(jPanel)
        }
    }
}
