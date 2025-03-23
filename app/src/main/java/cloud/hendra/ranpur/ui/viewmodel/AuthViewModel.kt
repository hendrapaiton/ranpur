package cloud.hendra.ranpur.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cloud.hendra.ranpur.domain.usecase.LoginUseCase
import cloud.hendra.ranpur.domain.usecase.UserUseCase
import cloud.hendra.ranpur.utils.auth.Result
import cloud.hendra.ranpur.utils.auth.TokenManager
import cloud.hendra.ranpur.utils.state.GuardState
import cloud.hendra.ranpur.utils.state.UserState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val loginUseCase: LoginUseCase,
    private val userUseCase: UserUseCase,
    private val tokenManager: TokenManager
) : ViewModel() {
    private val _authState = MutableStateFlow<GuardState>(GuardState.Idle)
    val authState: StateFlow<GuardState> = _authState
    private val _userState = MutableStateFlow<UserState>(UserState.Idle)
    val userState: StateFlow<UserState> = _userState

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _authState.value = GuardState.Loading

            when (val result = loginUseCase(email, password)) {
                is Result.Success -> {
                    tokenManager.saveAccessToken(result.data)
                    _authState.value = GuardState.Authenticated
                }

                is Result.Error -> _authState.value =
                    GuardState.Error(result.message ?: "Unknown error")

                else -> _authState.value = GuardState.Loading

            }
        }
    }

    fun user() {
        viewModelScope.launch {
            _userState.value = UserState.Idle
            when (val result = userUseCase()) {
                is Result.Success -> {
                    _authState.value = GuardState.Authenticated
                    _userState.value = UserState.Success(result.data)
                }

                is Result.Error -> {
                    _authState.value = GuardState.Unauthenticated
                    _userState.value = UserState.Error(result.message.toString())
                }

                else -> {
                    _userState.value = UserState.Idle
                    _authState.value = GuardState.Unauthenticated
                }
            }
        }
    }
}