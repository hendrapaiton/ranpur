package cloud.hendra.ranpur

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.navigation.compose.rememberNavController
import cloud.hendra.ranpur.ui.navigation.Navigation
import cloud.hendra.ranpur.ui.theme.RanPurTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            RanPurTheme {
                Surface {
                    Navigation(navController = navController)
                }
            }
        }
    }
}
