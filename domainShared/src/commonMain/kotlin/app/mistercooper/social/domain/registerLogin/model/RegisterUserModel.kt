package app.mistercooper.social.domain.registerLogin.model

data class RegisterUserModel(val apikey: String, val name: String? = null, val email: String? = null, val password: String? = null, val imageProfile: ByteArray? = null)
