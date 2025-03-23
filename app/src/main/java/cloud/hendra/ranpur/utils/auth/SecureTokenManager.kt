package cloud.hendra.ranpur.utils.auth

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

class SecureTokenManager(private val context: Context) : TokenManager {
    private val prefs: SharedPreferences by lazy {
        createEncryptedSharedPreferences(context)
    }

    override fun getAccessToken(): String? {
        return prefs.getString(KEY_ACCESS_TOKEN, null)
    }

    override fun saveAccessToken(token: String) {
        Log.d("TokenManager", "Saving token: $token")
        prefs.edit {
            putString(KEY_ACCESS_TOKEN, token)
        }
    }

    override fun clearAccessToken() {
        prefs.edit {
            remove(KEY_ACCESS_TOKEN)
            apply()
        }
    }

    private fun createEncryptedSharedPreferences(context: Context): SharedPreferences {
        val masterKey = MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()
        return EncryptedSharedPreferences.create(
            context,
            PREFS_FILE_NAME,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    companion object {
        private const val PREFS_FILE_NAME = "secure_token"
        private const val KEY_ACCESS_TOKEN = "access_token"
    }
}