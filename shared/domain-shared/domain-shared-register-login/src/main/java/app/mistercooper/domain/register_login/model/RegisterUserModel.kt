package app.mistercooper.domain.register_login.model

import androidx.annotation.Keep
import java.io.File
import java.io.Serializable

@Keep
data class RegisterUserModel(val name: String, val email: String, val password: String, val imageProfile: File): Serializable
