package cloud.hendra.ranpur

import android.app.Application
import cloud.hendra.ranpur.data.module.networkModule
import cloud.hendra.ranpur.data.module.repositoryModule
import cloud.hendra.ranpur.data.module.serviceModule
import cloud.hendra.ranpur.data.module.useCaseModule
import cloud.hendra.ranpur.data.module.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(
                listOf(
                    networkModule,
                    serviceModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}