package core.di

import record.audio.di.audioModule
import record.home.di.homeModule
import record.image.di.imageModule
import record.settings.di.settingsModule
import record.video.di.videoModule

val recordModules = listOf(
    homeModule,
    videoModule,
    imageModule,
    settingsModule,
    audioModule
)