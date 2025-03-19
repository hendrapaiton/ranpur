package cloud.hendra.ranpur.data.module

import cloud.hendra.ranpur.utils.Constant.Companion.BASE_URL
import cloud.hendra.ranpur.utils.auth.AuthInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single<OkHttpClient> {
        OkHttpClient.Builder()
            .addInterceptor(
                AuthInterceptor(
                    tokenManager = get(),
                    excludedPaths = setOf(
                        "/api/v1",
                        "/api/v1/login"
                    )
                )
            )
            .addInterceptor(HttpLoggingInterceptor().setLevel(Level.BODY))
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}