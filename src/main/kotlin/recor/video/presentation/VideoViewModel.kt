package recor.video.presentation

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import probe.domain.ProbRepository
import probe.domain.WindowPlacement
import probe.domain.model.Screen
import recor.home.presentation.event.RecordingFrameEvent
import recor.video.domain.VideoRepository
import recor.video.presentation.event.VideoEvent
import recor.video.presentation.state.VideoState

class VideoViewModel : KoinComponent {

    private val videoRepository: VideoRepository by inject()
    private val probRepository: ProbRepository by inject()

    private val _state = MutableStateFlow(VideoState())
    val state: StateFlow<VideoState> = _state.asStateFlow()

    fun onEvent(event: VideoEvent) {
        when (event) {
            is VideoEvent.Record -> {
                videoRepository.recordScreenWithTimeout(
                    event.config,
                    windowPlacement= event.windowPlacement,
                    selectedScreen = _state.value.selectedScreen,
                )
            }

            is VideoEvent.RecordSection -> {
                videoRepository.recordScreenWithTimeout(
                    config = event.config,
                    windowPlacement = event.windowPlacement,
                    selectedScreen = _state.value.selectedScreen,
                )
            }

            is VideoEvent.StartRecording -> {
                videoRepository.startRecording(
                    config = event.config,
                    windowPlacement = event.bounds,
                    selectedScreen = _state.value.selectedScreen,
                )
            }

            is VideoEvent.SelectScreen -> {
                selectScreen(event.screenId)
            }

            VideoEvent.StopRecording -> {
                videoRepository.stopRecording()
            }

            is VideoEvent.RecordAllWindows -> {

            }

            is VideoEvent.RecordAudio -> {

            }

            VideoEvent.RecordDevice -> {

            }

            is VideoEvent.GetVideosByPath -> {
                _state.value = _state.value.copy(
                    videos = videoRepository.getVideosByPath(event.path),
                )
            }
        }
    }

    fun onRecordingFrameEvent(event: RecordingFrameEvent) {
        when (event) {
            is RecordingFrameEvent.UpdateWindowPlacement -> {
                _state.value = _state.value.copy(
                    recordingArea = WindowPlacement(
                        x = event.x,
                        y = event.y,
                        width = event.width,
                        height = event.height
                    ),
                    selectedScreen = Screen(
                        id = if (event.x >= 0) "0" else "1",
                        name = "Screen ${if (event.x >= 0) "0" else "1"}",
                        width = _state.value.selectedScreen.width,
                        height = _state.value.selectedScreen.height,
                    ),
                )
            }
        }
    }

    private fun selectScreen(screenId: String) {
        val selectedScreen = probRepository.getScreens().find { it.id == screenId } ?: return
        _state.value = _state.value.copy(
            selectedScreen = selectedScreen,
        )
    }

    private fun getScreens() {
        _state.value = _state.value.copy(
            screens = probRepository.getScreens(),
            isLoading = false,
        )
    }

}