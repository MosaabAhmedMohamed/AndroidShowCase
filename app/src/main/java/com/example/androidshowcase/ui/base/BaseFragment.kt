package com.example.androidshowcase.ui.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.androidshowcase.di.Injectable
import com.example.androidshowcase.ui.custom.RetryDialog
import com.example.androidshowcase.util.ViewStatus
import dagger.android.AndroidInjection
import timber.log.Timber
import javax.inject.Inject

abstract class BaseFragment constructor(
    @LayoutRes
    private val layoutRes: Int
) : Fragment(layoutRes),Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var args: Bundle

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(activity)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    protected abstract fun init()
    protected abstract fun onViewClicked()

    open fun showLoading(visibility: Boolean) {
        activity?.let {
            if (visibility) (activity as BaseActivity).showLoading()
            else (activity as BaseActivity).hideLoading()
        }
    }

     fun showRetryDialog(retry: Boolean) {
        if (retry) RetryDialog.newInstance()
            .show(childFragmentManager, RetryDialog::class.simpleName)
    }

    open fun navigate(action: Int, args: Bundle? = null) {
        findNavController().navigate(action, args)
    }


}