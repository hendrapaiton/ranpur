package cloud.hendra.ranpur.utils.state

import cloud.hendra.ranpur.data.remote.dto.UserDto


sealed class UserState {
    object Idle : UserState()
    object Loading : UserState()
    data class Success(val data: UserDto) : UserState()
    data class Error(val message: String) : UserState()
}