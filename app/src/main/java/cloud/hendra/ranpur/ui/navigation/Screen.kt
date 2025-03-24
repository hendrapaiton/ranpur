package cloud.hendra.ranpur.ui.navigation

sealed class Screen(val route: String) {
    object Loading : Screen("loading")
    object Login : Screen("login")
    object Product : Screen("product")
    object Stock : Screen("stock")
    object Sales : Screen("sales")
}