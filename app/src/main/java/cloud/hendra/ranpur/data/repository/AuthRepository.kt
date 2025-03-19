package cloud.hendra.ranpur.data.repository

import cloud.hendra.ranpur.data.remote.dto.LoginResponse
import cloud.hendra.ranpur.utils.auth.Result

interface AuthRepository {
    suspend fun login(email: String, password: String): Result<LoginResponse>
}