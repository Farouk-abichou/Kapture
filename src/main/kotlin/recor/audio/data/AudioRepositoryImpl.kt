package recor.audio.data

import core.util.FileHelper.getFileDate
import core.util.FileHelper.getFileSize
import core.util.FileHelper.getFilesWithExtension
import probe.domain.model.Screen
import recor.audio.domain.AudioRepository
import recor.audio.domain.model.Audio
import recor.video.domain.model.RecordSettings
import java.io.BufferedReader
import java.io.InputStreamReader
import java.nio.file.Path

class AudioRepositoryImpl : AudioRepository {
    override fun getAudioByPath(filePath: String): List<Audio> {
        val audios = getFilesWithExtension(filePath, listOf(".mp3", ".wav"))

        return audios.map { path ->
            Audio(
                name = path.fileName.toString(),
                path = path.toString(),
                size = getFileSize(path),
                date = getFileDate(path),
                duration = getAudioDuration(path),
            )
        }
    }

    override fun recordAudioWithTimeout(config: RecordSettings?) {
//        val command = arrayOf("/bin/sh", "-c", "ffmpeg -i \"${config.outputFile}\" -t ${config.durationInSeconds} \"${config.outputFile}\"")
//        val process = Runtime.getRuntime().exec(command)
//        process.waitFor()
    }

    override fun startAudioRecording(
        config: RecordSettings,
        selectedScreen: Screen
    ) {

    }

    override fun stopAudioRecording() {

    }

    private fun getAudioDuration(path: Path): String {
        val command = arrayOf("/bin/sh", "-c", "ffmpeg -i \"${path.toAbsolutePath()}\" 2>&1 | grep Duration")
        val process = Runtime.getRuntime().exec(command)
        val reader = BufferedReader(InputStreamReader(process.inputStream))

        val durationLine = reader.readLine() ?: return "Unknown duration"

        return durationLine.substringAfter("Duration: ").substringBefore(",").trim()
    }

}