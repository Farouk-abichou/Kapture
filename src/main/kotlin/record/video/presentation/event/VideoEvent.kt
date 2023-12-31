package record.video.presentation.event

import probe.core.WindowPlacement
import record.video.domain.model.RecordSettings

sealed class VideoEvent {
    data object Record : VideoEvent()
    data object RecordDevice: VideoEvent()
    data object StopRecording : VideoEvent()
    data object SelectScreenSection : VideoEvent()
    data class SelectVideo(val path: String) : VideoEvent()
    data class DeleteVideo(val path: String) : VideoEvent()
    data class GetVideosByPath(val path: String) : VideoEvent()
    data class SelectScreen(val screenId: String) : VideoEvent()
    data class ChangeVideosLocation(val path: String) : VideoEvent()
    data class RecordAllWindows(val config: RecordSettings) : VideoEvent()
    data class StartRecording(val bounds: WindowPlacement?) : VideoEvent()
    data class RecordSection(val config: RecordSettings, val windowPlacement: WindowPlacement?) : VideoEvent()
}