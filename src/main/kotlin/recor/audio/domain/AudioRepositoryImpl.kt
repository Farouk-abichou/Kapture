package recor.audio.domain

import core.util.FilePaths
import recor.audio.data.AudioRepository
import recor.audio.domain.model.Audio
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.attribute.BasicFileAttributes
import java.util.stream.Collectors

class AudioRepositoryImpl : AudioRepository {
    override fun getAudiosByPath(filePath: String): List<Audio> {
        val videos = Files.walk(Paths.get(filePath))
            .filter { path -> path.toString().endsWith(".mp4") }
            .collect(Collectors.toList())

        return videos.map { path ->
            Audio(
                name = path.fileName.toString(),
                path = path.toString(),
                size = getVideoSize(path).toString(),        // Implement this
                date = getVideoDate(path),        // Implement this
                duration = getVideoDuration(path),
                thumbnail = getVideoThumbnail(path)
            )
        }
    }

    // Pseudocode for helper functions
    private fun getVideoDuration(path: Path): String {
        val command = arrayOf("/bin/sh", "-c", "ffmpeg -i \"${path.toAbsolutePath()}\" 2>&1 | grep Duration")
        val process = Runtime.getRuntime().exec(command)
        val reader = BufferedReader(InputStreamReader(process.inputStream))

        val durationLine = reader.readLine() ?: return "Unknown duration"

        return durationLine.substringAfter("Duration: ").substringBefore(",").trim()
    }

    private fun getVideoSize(path: Path): Long {
        return Files.size(path)
    }

    private fun getVideoDate(path: Path): String {
        val attr = Files.readAttributes(path, BasicFileAttributes::class.java)
        return attr.creationTime().toString()
    }

    private fun getVideoThumbnail(path: Path, timestamp: String = "00:00:02"): String {
        val outputFilePath = "${FilePaths.VideosPath}/${path.fileName.toString().replace(".mp4", ".jpg")}"
        val command = "ffmpeg -i \"${path.toAbsolutePath()}\" -ss $timestamp -vframes 1 \"$outputFilePath\""

        try {
            val process = Runtime.getRuntime().exec(command)
            process.waitFor()
        } catch (e: Exception) {
            e.printStackTrace()
            return ""
        }

        return if (File(outputFilePath).exists()) outputFilePath else ""
    }
}