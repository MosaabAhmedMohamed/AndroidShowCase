package com.example.androidshowcase.ui.home

import android.os.Bundle
import androidx.fragment.app.FragmentFactory
import com.example.androidshowcase.BaseApplication
import com.example.androidshowcase.R
import com.example.androidshowcase.ui.base.BaseActivity
import com.example.androidshowcase.ui.home.fragment_factory.HomeNavHostFragment
import javax.inject.Inject
import javax.inject.Named

class HomeActivity : BaseActivity() {


    @Inject
    @Named("HomeFragmentFactory")
    lateinit var homeFragmentFactory: FragmentFactory


    override fun inject() {
        (application as BaseApplication).homeComponent()
            .inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        onRestoreInstanceState()
    }

    private fun onRestoreInstanceState() {
        val host = supportFragmentManager.findFragmentById(R.id.home_nav_host_fragment)
        host?.let {
            // do nothing
        } ?: createNavHost()
    }

    private fun createNavHost() {
        val navHost = HomeNavHostFragment.create(
            R.navigation.home_nav_graph
        )
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.home_nav_host_fragment,
                navHost,
                getString(R.string.HomeNavHost)
            )
            .setPrimaryNavigationFragment(navHost)
            .commit()
    }

}