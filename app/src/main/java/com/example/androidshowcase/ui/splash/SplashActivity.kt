package com.example.androidshowcase.ui.splash

import android.content.Intent
import android.os.Bundle
import com.example.androidshowcase.BaseApplication
import com.example.androidshowcase.R
import com.example.androidshowcase.ui.base.BaseActivity
import com.example.androidshowcase.ui.home.HomeActivity

class SplashActivity : BaseActivity() {

    override fun inject() {
        (application as BaseApplication)
            .splashComponent()
            .inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        goToHome()
    }

    fun goToHome() {
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
        (application as BaseApplication).releaseSplashComponent()
    }
}