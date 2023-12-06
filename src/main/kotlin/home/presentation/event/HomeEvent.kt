package home.presentation.event

import record.domain.ConfigurationManager
import screen.domain.WindowBounds

sealed class HomeEvent {
    data class RecordSection(
        val config: ConfigurationManager, val bounds: WindowBounds?
    ) : HomeEvent()
    data class Record(val config: ConfigurationManager) : HomeEvent()
    data class RecordWithAudio(val config: ConfigurationManager, val bounds: WindowBounds?, val audioSource: String) : HomeEvent()
    data class StartRecording(val config: ConfigurationManager, val bounds: WindowBounds?) : HomeEvent()
    data class SelectScreen(val screenId: String) : HomeEvent()
    data object StopRecording : HomeEvent()
    data object DiscardRecording: HomeEvent()

    data class SaveRecording(
        val outputFilePath: String
    ): HomeEvent()

    data object PauseRecording: HomeEvent()

    data class ResumeRecording(val config: ConfigurationManager, val bounds: WindowBounds?): HomeEvent()

    data class SetRecordingArea(val bounds: WindowBounds) : HomeEvent()

}