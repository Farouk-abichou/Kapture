package core

import androidx.compose.ui.graphics.ImageBitmap

interface MediaItem {
    val name: String
    val path: String
    val size: Long
    val duration: Double
    val thumbnail: ImageBitmap
    val dateCreated: String

    fun play()
    fun stop()
    fun getInfo(): String
}