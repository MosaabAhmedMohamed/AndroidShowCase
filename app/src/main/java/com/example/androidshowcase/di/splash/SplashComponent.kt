package com.example.androidshowcase.di.splash

import com.example.androidshowcase.ui.splash.SplashActivity
import dagger.Subcomponent

@SplashScope
@Subcomponent
interface SplashComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): SplashComponent
    }

    fun inject(splashActivity: SplashActivity)
}