package com.uandcode.messenger.common.android.di

import com.uandcode.messenger.common.android.AndroidLogger
import com.uandcode.messenger.common.android.CoreStringProviderImpl
import com.uandcode.messenger.core.essentials.exceptions.mapper.DefaultExceptionToMessageMapper
import com.uandcode.messenger.core.essentials.exceptions.mapper.ExceptionToMessageMapper
import com.uandcode.messenger.core.essentials.logger.Logger
import com.uandcode.messenger.core.essentials.resources.CoreStringProvider
import com.uandcode.messenger.core.essentials.resources.StringProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module
@InstallIn(SingletonComponent::class)
interface CommonAndroidModule {
    @Binds
    fun bindLogger(logger: AndroidLogger): Logger

    @Binds
    @IntoMap
    @ClassKey(CoreStringProvider::class)
    fun bindStringProvider(impl: CoreStringProviderImpl): StringProvider

    @Binds
    fun bindExceptionToMessageMapper(impl: DefaultExceptionToMessageMapper): ExceptionToMessageMapper
}