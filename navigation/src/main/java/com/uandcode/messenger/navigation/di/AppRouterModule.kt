package com.uandcode.messenger.navigation.di

import com.uandcode.messenger.navigation.base.AppRouter
import com.uandcode.messenger.navigation.base.NavComponentAppRouter
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
interface AppRouterModule {
    @Binds
    fun bindAppRouter(impl: NavComponentAppRouter): AppRouter
}