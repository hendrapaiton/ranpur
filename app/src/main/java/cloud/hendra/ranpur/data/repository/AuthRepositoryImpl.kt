package cloud.hendra.ranpur.data.repository

import cloud.hendra.ranpur.data.remote.AuthService
import cloud.hendra.ranpur.data.remote.dto.LoginRequest
import cloud.hendra.ranpur.data.remote.dto.UserDto
import cloud.hendra.ranpur.utils.auth.Result

class AuthRepositoryImpl(
    private val authService: AuthService
) : AuthRepository {
    override suspend fun login(
        email: String,
        password: String
    ): Result<String> {
        return try {
            val response = authService.login(LoginRequest(email, password))
            if (response.isSuccessful) {
                val data = response.body()!!
                Result.Success(data.token)
            } else {
                Result.Error(response.message())
            }
        } catch (e: Exception) {
            Result.Error(e.message)
        }
    }

    override suspend fun user(): Result<UserDto> {
        return try {
            val response = authService.user()
            if (response.isSuccessful) {
                Result.Success(response.body()!!)
            } else {
                Result.Error(response.message())
            }
        } catch (e: Exception) {
            Result.Error(e.message.toString())
        }
    }
}