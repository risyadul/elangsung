package com.services.elangsung.view.common.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.annotation.CallSuper
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.viewbinding.ViewBinding
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseFragment<V : ViewBinding> : Fragment() {

    lateinit var binding: V

    private val compositeDisposable = CompositeDisposable()

    protected abstract fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): V

    protected open fun onTrackScreenOpened() = Unit

    protected open fun onBackPressed() {
        try {
            if (!findNavController().navigateUp()) {
                requireActivity().finish()
            }
        } catch (e: IllegalStateException) {
            Log.d(BaseFragment::class.java.simpleName, e.message ?: String())
            requireActivity().finish()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = getViewBinding(inflater, container)
        return binding.root
    }

    /**
     * function to configure individual toolbar in a fragment.
     * If all the fragments in one navigation group have the same configuration,
     * better set up the toolbar in the parent activity by overriding
     * [BaseActivity.configureActionBar] and [BaseActivity.getActionToolbar] methods
     *
     * @param toolbar the toolbar in the fragment xml
     * @param hasMenu determine if the toolbar has menus
     * @param showTitle determine if the toolbar should show screen title
     * @param showBackButton determine if the toolbar should show back arrow button
     */
    protected fun initToolbar(
        toolbar: Toolbar,
        hasMenu: Boolean = true,
        showTitle: Boolean = true,
        showBackButton: Boolean = true
    ) {
        setHasOptionsMenu(hasMenu)
        val navHostFragment = findNavController()
        NavigationUI.setupWithNavController(toolbar, navHostFragment)
        with(activity as BaseActivity<*>) {
            setSupportActionBar(toolbar)
            supportActionBar?.apply {
                setHomeButtonEnabled(showBackButton)
                setDisplayHomeAsUpEnabled(showBackButton)
                setDisplayShowTitleEnabled(showTitle)
            }
        }

        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    onBackPressed()
                }
            }
        )
        initViews()
    }

    @CallSuper
    protected open fun initViews() {
    }

    fun Disposable.collect() = compositeDisposable.add(this)

    protected fun cleanDisposable() {
        compositeDisposable.dispose()
    }

    override fun onDestroy() {
        super.onDestroy()
        cleanDisposable()
    }
}
