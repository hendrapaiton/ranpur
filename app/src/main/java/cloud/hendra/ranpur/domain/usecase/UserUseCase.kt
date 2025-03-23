package cloud.hendra.ranpur.domain.usecase

import cloud.hendra.ranpur.data.repository.AuthRepository

class UserUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke() = repository.user()
}