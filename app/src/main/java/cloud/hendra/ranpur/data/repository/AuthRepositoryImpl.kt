package cloud.hendra.ranpur.data.repository

import cloud.hendra.ranpur.data.remote.AuthService
import cloud.hendra.ranpur.data.remote.dto.LoginRequest
import cloud.hendra.ranpur.data.remote.dto.LoginResponse
import cloud.hendra.ranpur.utils.auth.Result

class AuthRepositoryImpl(
    private val authService: AuthService
) : AuthRepository {
    override suspend fun login(
        email: String,
        password: String
    ): Result<LoginResponse> {
        return try {
            val response = authService.login(LoginRequest(email, password))
            if (response.isSuccessful) {
                Result.Success(response.body()!!)
            } else {
                Result.Error(response.message())
            }
        } catch (e: Exception) {
            Result.Error(e.message)
        }
    }
}