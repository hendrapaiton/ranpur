package cloud.hendra.ranpur.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import cloud.hendra.ranpur.ui.screen.LoginPage
import cloud.hendra.ranpur.ui.screen.SalesPage

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        composable(route = "login") {
            LoginPage(onLoginSuccess = { navController.navigate("sales") })
        }
        composable(route = "product") {}
        composable(route = "stock") {}
        composable(route = "sales") {
            SalesPage()
        }
    }
}