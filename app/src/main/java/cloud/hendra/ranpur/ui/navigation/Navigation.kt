package cloud.hendra.ranpur.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import cloud.hendra.ranpur.ui.navigation.Routes.LOADING
import cloud.hendra.ranpur.ui.navigation.Routes.LOGIN
import cloud.hendra.ranpur.ui.navigation.Routes.PRODUCT
import cloud.hendra.ranpur.ui.navigation.Routes.SALES
import cloud.hendra.ranpur.ui.navigation.Routes.STOCK
import cloud.hendra.ranpur.ui.screen.LoadingPage
import cloud.hendra.ranpur.ui.screen.LoginPage
import cloud.hendra.ranpur.ui.screen.ProductPage
import cloud.hendra.ranpur.ui.screen.SalesPage
import cloud.hendra.ranpur.ui.screen.StockPage

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = LOADING
    ) {
        composable(route = LOADING) {
            LoadingPage()
        }
        composable(route = LOGIN) {
            LoginPage(onLoginSuccess = { navController.navigate(SALES) })
        }
        composable(route = PRODUCT) {
            ProductPage()
        }
        composable(route = STOCK) {
            StockPage()
        }
        composable(route = SALES) {
            SalesPage()
        }
    }
}