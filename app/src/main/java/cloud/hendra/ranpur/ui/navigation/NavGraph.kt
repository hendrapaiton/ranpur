package cloud.hendra.ranpur.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "sales"
    ) {
        composable(route = "login") {}
        composable(route = "product") {}
        composable(route = "stock") {}
        composable(route = "sales") {}
    }
}