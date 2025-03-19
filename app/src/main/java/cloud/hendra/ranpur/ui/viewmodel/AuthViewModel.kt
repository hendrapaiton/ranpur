package cloud.hendra.ranpur.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cloud.hendra.ranpur.domain.usecase.LoginUseCase
import cloud.hendra.ranpur.utils.auth.Result
import cloud.hendra.ranpur.utils.auth.TokenManager
import cloud.hendra.ranpur.utils.state.AuthState
import cloud.hendra.ranpur.utils.state.AuthState.*
import kotlinx.coroutines.launch

class AuthViewModel(
    private val loginUseCase: LoginUseCase,
    private val tokenManager: TokenManager
) : ViewModel() {
    private val _authState = mutableStateOf<AuthState>(Idle)
    val authState = _authState

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _authState.value = Idle

            when (val result = loginUseCase(email, password)) {
                is Result.Success -> {
                    tokenManager.saveAccessToken(result.data)
                    _authState.value = Success(result.data)
                }

                is Result.Error -> {
                    _authState.value = Error(result.message ?: "Unknown error")
                }

                else -> {
                    Result.Loading
                }
            }
        }
    }
}