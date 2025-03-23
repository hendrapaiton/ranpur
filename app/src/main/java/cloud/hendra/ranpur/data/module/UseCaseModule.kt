package cloud.hendra.ranpur.data.module

import cloud.hendra.ranpur.domain.usecase.LoginUseCase
import cloud.hendra.ranpur.domain.usecase.UserUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { LoginUseCase(get()) }
    single { UserUseCase(get()) }
}