package com.uandcode.messenger.navigation.base

import com.uandcode.messenger.navigation.Route

interface AppRouter {
    fun launch(route: Route)
    fun restart(route: Route)
    fun goBack()
}