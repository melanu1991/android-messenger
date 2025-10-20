package com.uandcode.messenger.navigation.routers

import com.uandcode.messenger.features.init.InitRouter
import com.uandcode.messenger.navigation.SignInRoute
import com.uandcode.messenger.navigation.base.AppRouter
import javax.inject.Inject

class InitRouterImpl @Inject constructor(
    private val appRouter: AppRouter
): InitRouter {
    override fun launchSignIn() {
        appRouter.restart(SignInRoute)
    }
}