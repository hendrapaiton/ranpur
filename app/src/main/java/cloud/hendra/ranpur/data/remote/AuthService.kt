package cloud.hendra.ranpur.data.remote

import cloud.hendra.ranpur.data.remote.dto.LoginRequest
import cloud.hendra.ranpur.data.remote.dto.LoginResponse
import cloud.hendra.ranpur.data.remote.dto.UserDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthService {
    @POST("api/v1/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @GET("api/v1/user")
    suspend fun user(): Response<UserDto>
}