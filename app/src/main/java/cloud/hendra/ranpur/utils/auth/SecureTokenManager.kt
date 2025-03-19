package cloud.hendra.ranpur.utils.auth

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import cloud.hendra.ranpur.data.remote.dto.LoginResponse

class SecureTokenManager(private val context: Context) : TokenManager {
    private val prefs: SharedPreferences by lazy {
        EncryptedSharedPreferences.create(
            context,
            "secure_token",
            MasterKey.Builder(context).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build(),
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    override fun getAccessToken(): String? = prefs.getString("TOKEN", null)

    override fun saveAccessToken(login: LoginResponse) {
        prefs.edit() { putString("TOKEN", login.token) }
    }

    override fun clearAccessToken() {
        prefs.edit() { remove("TOKEN") }
    }
}