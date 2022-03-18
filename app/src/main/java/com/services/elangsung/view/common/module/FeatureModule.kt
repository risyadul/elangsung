package com.services.elangsung.view.common.module

import com.services.elangsung.data.authentication.AuthenticationModule
import dagger.Module

@Module(includes = [AuthenticationModule::class])
class FeatureModule {
}
