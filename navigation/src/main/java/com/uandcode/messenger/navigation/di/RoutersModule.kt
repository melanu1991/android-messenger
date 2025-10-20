package com.uandcode.messenger.navigation.di

import com.uandcode.messenger.features.init.InitRouter
import com.uandcode.messenger.navigation.routers.InitRouterImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface RoutersModule {
    @Binds
    fun bindInitRouter(impl: InitRouterImpl): InitRouter
}