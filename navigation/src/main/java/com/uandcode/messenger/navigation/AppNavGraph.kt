package com.uandcode.messenger.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.uandcode.messenger.features.init.InitScreen
import com.uandcode.messenger.features.signin.SigninScreen

fun NavGraphBuilder.buildAppNavGraph() {
    composable<InitRoute> { InitScreen()
//        onLaunchSignInScreenAction = {
//        navController.navigate(SignInRoute) {
//            popUpTo(0)
//        }
//    })
    }
    composable<SignInRoute> { SigninScreen() }
}