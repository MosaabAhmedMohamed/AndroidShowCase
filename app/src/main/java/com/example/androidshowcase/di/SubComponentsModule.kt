package com.example.androidshowcase.di

import com.example.androidshowcase.di.home.HomeComponent
import com.example.androidshowcase.di.splash.SplashComponent
import dagger.Module

@Module(
    subcomponents = [
        SplashComponent::class,
        HomeComponent::class
    ]
)
class SubComponentsModule