package probe.camera.di

import org.koin.dsl.module
import probe.screen.data.CameraRepositoryImpl
import probe.camera.domain.CameraRepository

val cameraModule = module {
    single<CameraRepository> { CameraRepositoryImpl() }
}