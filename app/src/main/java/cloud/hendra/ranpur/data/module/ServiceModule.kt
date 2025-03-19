package cloud.hendra.ranpur.data.module

import cloud.hendra.ranpur.data.remote.AuthService
import org.koin.dsl.module
import retrofit2.Retrofit

val serviceModule = module {
    single { get<Retrofit>().create(AuthService::class.java) }
}