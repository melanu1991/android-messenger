package com.uandcode.messenger.navigation.base

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.uandcode.messenger.navigation.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ActivityRetainedScoped
import java.lang.Compiler.command
import javax.inject.Inject

@ActivityRetainedScoped
class NavComponentAppRouter @Inject constructor(): AppRouter {

    private var navController: NavController? = null
    private val commands = mutableListOf<(NavController) -> Unit>()

    override fun launch(route: Route) = execute {
        it.navigate(route)
    }

    override fun restart(route: Route) = execute {
        it.navigate(route) {
            popUpTo(0)
        }
    }

    override fun goBack() = execute {
        it.navigateUp()
    }

    fun setNavController(navController: NavController?) {
        this.navController = navController
        if (navController != null) {
            commands.toList().forEach { command ->
                command.invoke(navController)
            }
            commands.clear()
        }
    }

    private fun execute(command: (NavController) -> Unit) {
        val navController = this.navController
        if (navController != null) {
            command.invoke(navController)
        } else {
            commands.add(command)
        }
    }

    @HiltViewModel
    class VM @Inject constructor(
        val appRouter: NavComponentAppRouter
    ): ViewModel()

    companion object {
        @Composable
        fun get(): NavComponentAppRouter {
            return hiltViewModel<VM>().appRouter
        }
    }
}