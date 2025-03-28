package cloud.hendra.ranpur.utils.auth

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AuthInterceptor(
    private val tokenManager: TokenManager,
    private val excludedPaths: Set<String> = setOf(
        "/api/v1",
        "/api/v1/login"
    )
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val pathSegments = request.url.pathSegments
        val requestPath = "/${pathSegments.joinToString("/")}"
        val newRequestBuilder = request.newBuilder().addHeader("Accept", "application/json")
        return if (shouldAddToken(requestPath)) {
            addTokenToRequest(chain, newRequestBuilder.build())
        } else {
            chain.proceed(newRequestBuilder.build())
        }
    }

    private fun shouldAddToken(request: String): Boolean {
        return excludedPaths.none { excluded ->
            request == excluded
        }
    }

    private fun addTokenToRequest(chain: Interceptor.Chain, originalRequest: Request): Response {
        return tokenManager.getAccessToken()?.let { token ->
            val newRequest = originalRequest.newBuilder()
                .header("Authorization", "Bearer $token")
                .build()
            chain.proceed(newRequest)
        } ?: chain.proceed(originalRequest)
    }
}