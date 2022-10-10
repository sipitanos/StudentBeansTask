package com.example.studentbeans

import android.app.Application
import com.example.studentbeans.koin.postsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class StudentBeansApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin{ // Koin has to be started when the application starts because it has to be alive all the time
            androidLogger()
            androidContext(this@StudentBeansApplication)
            modules(listOf(postsViewModel)) // list in case we add more koin modules in the future
        }
    }
}
