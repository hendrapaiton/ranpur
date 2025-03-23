package cloud.hendra.ranpur.data.remote.dto

import com.google.gson.annotations.SerializedName

data class UserDto(
    @SerializedName("email") val email: String,
    @SerializedName("name") val name: String,
    @SerializedName("role") val role: String,
)