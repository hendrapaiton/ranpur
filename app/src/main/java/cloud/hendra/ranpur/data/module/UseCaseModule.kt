package cloud.hendra.ranpur.data.module

import cloud.hendra.ranpur.domain.usecase.LoginUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { LoginUseCase(get()) }
}