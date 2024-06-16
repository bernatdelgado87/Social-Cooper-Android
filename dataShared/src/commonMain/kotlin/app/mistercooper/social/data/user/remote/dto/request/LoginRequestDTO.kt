package app.mistercooper.data.register_login.remote.dto.request

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequestDTO(val email: String, val password: String)
