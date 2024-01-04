package record.video.presentation.component

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.ComposeWindow
import androidx.compose.ui.unit.dp
import com.sun.javafx.application.PlatformImpl
import core.components.ComposeJFXPanel
import core.components.helper.rememberMediaPlayer
import core.util.FilePaths
import javafx.embed.swing.JFXPanel
import javafx.scene.Scene
import record.video.presentation.component.player.VideoPlayerControls
import java.io.File

@Composable
fun VideoPlayer(composeWindow: ComposeWindow) {
    // This finishListener ensures that the JavaFX event loop doesn't terminate unexpectedly.
    val finishListener = object : PlatformImpl.FinishListener {
        override fun idle(implicitExit: Boolean) {}
        override fun exitCalled() {}
    }

    DisposableEffect(Unit) {
        PlatformImpl.addListener(finishListener)

        onDispose { PlatformImpl.removeListener(finishListener) }
    }

    val jfxPanel = remember { JFXPanel() }

    val mediaPath = File(FilePaths.VideosPath + "/Screen.mp4").toURI().toString()
    val player =  rememberMediaPlayer(mediaPath)

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(500.dp, 500.dp),
            contentAlignment = Alignment.Center,
        ){
            ComposeJFXPanel(
                modifier  = Modifier.matchParentSize(),
                composeWindow = composeWindow,
                jfxPanel = jfxPanel,
                onCreate = { jfxPanel.scene = Scene(player) },
                onDestroy = {}
            )
        }

        VideoPlayerControls(player = player)
    }
}