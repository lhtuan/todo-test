package com.test.todo

import android.app.Application
import com.test.todo.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(
                appModule,
                localSourceModule,
                repositoryModule,
                useCaseModule,
                viewModelModule,
            )
        }
    }
}