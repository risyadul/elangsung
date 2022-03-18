package com.services.elangsung.view.common.module

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [FeatureModule::class])
interface ViewModelFactoryComponent {
}
