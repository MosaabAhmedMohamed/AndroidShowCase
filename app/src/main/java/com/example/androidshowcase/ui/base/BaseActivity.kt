package com.example.androidshowcase.ui.base

import android.app.ProgressDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidshowcase.BaseApplication
import com.example.androidshowcase.ui.custom.LoadingProgressDialog.Companion.showLoadingDialog
import com.example.androidshowcase.ui.custom.RetryDialog

abstract class BaseActivity : AppCompatActivity() {

    private var mProgressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    open fun hideLoading() {
        if (mProgressDialog != null && mProgressDialog!!.isShowing) {
            mProgressDialog!!.cancel()
        }
    }

    open fun showLoading() {
        hideLoading()
        mProgressDialog = showLoadingDialog(this)
    }

    open fun showRetryDialog(retry: Boolean) {
        if (retry) RetryDialog.newInstance().show(supportFragmentManager, RetryDialog::class.simpleName)
    }

}