package com.example.androidshowcase.di.home

import com.example.androidshowcase.ui.home.HomeActivity
import dagger.Subcomponent

@HomeScope
@Subcomponent(
    modules = [HomeModule::class,
        HomeViewModelModule::class,
        HomeFragmentsModule::class]
)
interface HomeComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): HomeComponent
    }

    fun inject(homeActivity: HomeActivity)

}