package cloud.hendra.ranpur.utils.auth

import cloud.hendra.ranpur.data.remote.dto.LoginResponse

interface TokenManager {
    fun getAccessToken(): String?
    fun saveAccessToken(token: LoginResponse)
    fun clearAccessToken()
}