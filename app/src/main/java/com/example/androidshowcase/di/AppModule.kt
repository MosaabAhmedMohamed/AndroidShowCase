package com.example.androidshowcase.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.example.androidshowcase.R
import com.example.androidshowcase.util.AuthenticationInterceptor
import com.example.androidshowcase.util.Constants
import com.example.androidshowcase.util.PreferenceKeys
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
object AppModule {

    @JvmStatic
    @Singleton
    @Provides
    fun provideSharedPrefrences(application: Application): SharedPreferences {
        return application.getSharedPreferences(
            PreferenceKeys.APP_PREFERENCES,
            Context.MODE_PRIVATE
        )
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideSharedPrefEditor(sharedPreferences: SharedPreferences): SharedPreferences.Editor {
        return sharedPreferences.edit()
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create()
    }

    @JvmStatic
    @Singleton
    @Provides
    internal fun provideHttpClient(authenticationInterceptor: AuthenticationInterceptor): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.connectTimeout(15, TimeUnit.SECONDS)
        httpClient.readTimeout(15, TimeUnit.SECONDS)
        httpClient.writeTimeout(15, TimeUnit.SECONDS)
        httpClient.retryOnConnectionFailure(true)

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        httpClient.addInterceptor(authenticationInterceptor)
        httpClient.addInterceptor(logging)


        return httpClient.build()
    }


    @JvmStatic
    @Singleton
    @Provides
    fun provideRetrofitBuilder(httpClient: OkHttpClient, gsonBuilder: Gson): Retrofit.Builder {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .client(httpClient)
            .addConverterFactory(MoshiConverterFactory.create())

    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideRequestOptions(): RequestOptions {
        return RequestOptions
            .placeholderOf(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideGlideInstance(
        application: Application,
        requestOptions: RequestOptions
    ): RequestManager {
        return Glide.with(application)
            .setDefaultRequestOptions(requestOptions)
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideAuthenticationInterceptor(): AuthenticationInterceptor {
        return AuthenticationInterceptor(Constants.API_KEY)
    }
}

