package cloud.hendra.ranpur.utils.state

import cloud.hendra.ranpur.data.remote.dto.LoginResponse

sealed class AuthState {
    object Idle : AuthState()
    object Loading : AuthState()
    data class Success(val token: LoginResponse) : AuthState()
    data class Error(val message: String) : AuthState()
}