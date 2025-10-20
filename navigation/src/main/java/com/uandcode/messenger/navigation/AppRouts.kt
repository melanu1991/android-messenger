package com.uandcode.messenger.navigation

import kotlinx.serialization.Serializable

interface Route

@Serializable
data object InitRoute : Route

@Serializable
data object SignInRoute : Route

