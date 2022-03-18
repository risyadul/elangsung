package com.services.elangsung.view.common.base

import androidx.lifecycle.ViewModelProvider

abstract class BaseViewModelFactory : ViewModelProvider.Factory {

    //TODO uncomment this after inject new view model factory
//    protected val component by lazy { DaggerViewModelFactoryComponent.builder().build() }
}
