package com.uandcode.messenger.common.android.di

import com.uandcode.messenger.common.android.AndroidLogger
import com.uandcode.messenger.common.android.AndroidStringProvider
import com.uandcode.messenger.common.android.AndroidStringResources
import com.uandcode.messenger.common.android.StringResourcesV2Impl
import com.uandcode.messenger.core.essentials.logger.Logger
import com.uandcode.messenger.core.essentials.resources.StringProvider
import com.uandcode.messenger.core.essentials.resources.StringResources
import com.uandcode.messenger.core.essentials.resources.StringResourcesV2
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface CommonAndroidModule {
    @Binds
    fun bindLogger(logger: AndroidLogger): Logger

    @Binds
    fun bindStringResources(impl: AndroidStringResources): StringResources

    @Binds
    fun bindStringResourcesV2(impl: StringResourcesV2Impl): StringResourcesV2

    @Binds
    fun bindStringProvider(impl: AndroidStringProvider): StringProvider
}