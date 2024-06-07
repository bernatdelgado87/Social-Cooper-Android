package com.jetbrains.kmpapp.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class UserDTO (
    val id: Int,
    val name: String? = null,
    val profileImage: String? = null
)
