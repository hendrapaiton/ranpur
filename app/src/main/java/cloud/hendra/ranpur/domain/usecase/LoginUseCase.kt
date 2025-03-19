package cloud.hendra.ranpur.domain.usecase

import cloud.hendra.ranpur.data.repository.AuthRepository

class LoginUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(email: String, password: String) = repository.login(email, password)
}