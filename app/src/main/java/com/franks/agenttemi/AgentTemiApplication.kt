package com.franks.agenttemi

import android.app.Application
import avatarStateModule
import environmentModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import speechModule
import voiceModule

class AgentTemiApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AgentTemiApplication)
            modules(
                environmentModule,
                voiceModule,
                speechModule,
                avatarStateModule
            )
        }
    }
}