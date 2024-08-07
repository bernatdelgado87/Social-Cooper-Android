package app.mistercooper.data.register_login.remote.mapper

import app.mistercooper.data.register_login.remote.dto.request.LoginRequestDTO
import app.mistercooper.social.domain.registerLogin.model.LoginUserModel

fun LoginUserModel.toDTO() = LoginRequestDTO(email, password)
