package com.uandcode.messenger.features.init.presentation.di

import com.uandcode.messenger.core.essentials.resources.StringProvider
import com.uandcode.messenger.features.init.domain.InitStringProvider
import com.uandcode.messenger.features.init.presentation.InitStringProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module
@InstallIn(SingletonComponent::class)
interface InitStringResourceModule {
    @Binds
    @IntoMap
    @ClassKey(InitStringProvider::class)
    fun bindStringProvider(impl: InitStringProviderImpl): StringProvider
}