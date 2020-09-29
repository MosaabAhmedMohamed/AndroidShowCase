package com.example.androidshowcase.di

import com.example.androidshowcase.di.home.HomeFragmentsModule
import com.example.androidshowcase.di.home.HomeModule
import com.example.androidshowcase.di.home.HomeScope
import com.example.androidshowcase.di.home.HomeViewModelModule
import com.example.androidshowcase.di.splash.SplashScope
import com.example.androidshowcase.ui.home.HomeActivity
import com.example.androidshowcase.ui.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {



    /**
     * Injects [SplashActivity]
     *
     * @return an instance of [SplashActivity]
     */

    @SplashScope
    @ContributesAndroidInjector
    internal abstract fun splashActivity(): SplashActivity

    @HomeScope
    @ContributesAndroidInjector( modules = [HomeModule::class,
        HomeViewModelModule::class,
        HomeFragmentsModule::class])
    internal abstract fun homeActivity(): HomeActivity

}