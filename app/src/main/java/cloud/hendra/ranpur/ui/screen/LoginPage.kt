package cloud.hendra.ranpur.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cloud.hendra.ranpur.ui.viewmodel.AuthViewModel
import cloud.hendra.ranpur.utils.state.GuardState.Authenticated
import cloud.hendra.ranpur.utils.state.GuardState.Error
import cloud.hendra.ranpur.utils.state.GuardState.Idle
import cloud.hendra.ranpur.utils.state.GuardState.Loading
import cloud.hendra.ranpur.utils.state.GuardState.Unauthenticated
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginPage(onLoginSuccess: () -> Unit, viewModel: AuthViewModel = koinViewModel()) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val state by viewModel.authState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Selamat Datang".uppercase(),
            fontSize = 24.sp,
            fontWeight = FontWeight.ExtraBold,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = "Ternak Cuan",
            fontSize = 18.sp,
            fontWeight = FontWeight.ExtraLight,
            color = MaterialTheme.colorScheme.secondary,
            letterSpacing = 5.sp
        )
        Spacer(modifier = Modifier.height(24.dp))
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = password,
            visualTransformation = androidx.compose.ui.text.input.PasswordVisualTransformation(),
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(20.dp))
        when (val authState = state) {
            is Loading -> CircularProgressIndicator()
            is Error -> Text(text = authState.message.toString())
            is Authenticated -> {
                onLoginSuccess()
            }

            is Unauthenticated -> {
                Unit
            }

            is Idle -> {
                Button(
                    onClick = { viewModel.login(username, password) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Masuk".uppercase(),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )
                }
            }
        }
    }
}