package com.example.androidshowcase.di


import android.app.Application
import com.example.androidshowcase.di.home.HomeComponent
import com.example.androidshowcase.di.splash.SplashComponent
import com.example.androidshowcase.ui.base.BaseActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        SubComponentsModule::class
    ]
)
interface AppComponent {


    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(baseActivity: BaseActivity)

    fun homeComponent(): HomeComponent.Factory

    fun splashComponent(): SplashComponent.Factory


}
