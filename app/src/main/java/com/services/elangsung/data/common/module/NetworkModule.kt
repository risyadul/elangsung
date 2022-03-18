package com.services.elangsung.data.common.module

import com.services.elangsung.domain.common.module.CommonModule
import dagger.Module

@Module(includes = [CommonModule::class])
class NetworkModule {
}
