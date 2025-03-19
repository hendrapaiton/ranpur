package cloud.hendra.ranpur.data.module

import cloud.hendra.ranpur.utils.auth.SecureTokenManager
import cloud.hendra.ranpur.utils.auth.TokenManager
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val securityModule = module {
    single<TokenManager> {
        SecureTokenManager(
            context = androidContext()
        )
    }
}