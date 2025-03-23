package cloud.hendra.ranpur.utils.auth

interface TokenManager {
    fun getAccessToken(): String?
    fun saveAccessToken(token: String)
    fun clearAccessToken()
}