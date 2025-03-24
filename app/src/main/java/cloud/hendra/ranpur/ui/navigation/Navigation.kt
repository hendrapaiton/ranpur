package cloud.hendra.ranpur.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import cloud.hendra.ranpur.ui.screen.LoadingPage
import cloud.hendra.ranpur.ui.screen.LoginPage
import cloud.hendra.ranpur.ui.screen.ProductPage
import cloud.hendra.ranpur.ui.screen.SalesPage
import cloud.hendra.ranpur.ui.screen.StockPage
import cloud.hendra.ranpur.ui.viewmodel.AuthViewModel
import cloud.hendra.ranpur.utils.state.GuardState
import org.koin.androidx.compose.koinViewModel

@Composable
fun Navigation(navController: NavHostController, authViewModel: AuthViewModel = koinViewModel()) {
    val authState by authViewModel.authState.collectAsState()

    LaunchedEffect(authState) {
        when (authState) {
            is GuardState.Authenticated -> navController.navigate(Screen.Sales.route)
            is GuardState.Unauthenticated -> navController.navigate(Screen.Login.route)
            is GuardState.Loading -> navController.navigate(Screen.Loading.route)
            else -> navController.navigate(Screen.Loading.route)
        }
    }

    LaunchedEffect(key1 = Unit) {
        authViewModel.user()
    }

    NavHost(
        navController = navController,
        startDestination = Screen.Loading.route
    ) {
        appGraph(navController)
    }
}

fun androidx.navigation.NavGraphBuilder.appGraph(navController: NavHostController) {
    composable(route = Screen.Loading.route) {
        LoadingPage()
    }
    composable(route = Screen.Login.route) {
        LoginPage(onLoginSuccess = {
            navController.navigate(Screen.Sales.route) {
                popUpTo(Screen.Login.route) { inclusive = true }
            }
        })
    }
    composable(route = Screen.Product.route) {
        ProductPage()
    }
    composable(route = Screen.Stock.route) {
        StockPage()
    }
    composable(route = Screen.Sales.route) {
        SalesPage()
    }
}