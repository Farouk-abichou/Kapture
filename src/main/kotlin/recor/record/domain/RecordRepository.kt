package recor.record.domain

import probe.domain.model.Screen
import probe.domain.WindowBounds
import probe.domain.WindowPlacement
import recor.record.domain.model.ConfigurationManager

interface RecordRepository {
    fun startRecording(
        config: ConfigurationManager,
        bounds: WindowBounds?,
        recordingArea: WindowPlacement,
        selectedScreen: Screen
    )
    fun stopRecording()
    fun pauseRecording()
    fun resumeRecording(config: ConfigurationManager, bounds: WindowBounds?)
    fun discardRecording()
    fun saveRecording(outputFilePath: String)
    fun setRecordingArea(position: WindowPlacement)
    fun recordScreen(config: ConfigurationManager, bounds: WindowBounds?)
    fun recordScreenWithAudio(config: ConfigurationManager, bounds: WindowBounds?, audioSource: String)
}
