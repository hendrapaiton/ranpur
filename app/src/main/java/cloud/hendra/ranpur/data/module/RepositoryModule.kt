package cloud.hendra.ranpur.data.module

import cloud.hendra.ranpur.data.repository.AuthRepository
import cloud.hendra.ranpur.data.repository.AuthRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<AuthRepository> { AuthRepositoryImpl(get()) }
}