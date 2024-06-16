package app.mistercooper.social.data.user.remote.api

import app.mistercooper.data.register_login.remote.dto.request.LoginRequestDTO
import app.mistercooper.data.register_login.remote.dto.response.RegisterUserResponseDTO
import com.jetbrains.kmpapp.remote.api.CommonApi
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.forms.formData
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders

interface RegisterLoginApi {
    suspend fun registerUser(
        name: String, email: String, password: String, imageProfile: ByteArray, filename: String
    ): RegisterUserResponseDTO

    suspend fun login(
        body: LoginRequestDTO
    ): RegisterUserResponseDTO

}

class RegisterLoginApiImpl(private val client: HttpClient) : RegisterLoginApi{
    override suspend fun registerUser(
        name: String,
        email: String,
        password: String,
        imageProfile: ByteArray,
        filename: String
    ): RegisterUserResponseDTO {
        return try {
            client.post(CommonApi.USER_API_URL + "register") {
                val requestBody = MultiPartFormDataContent(
                    formData {
                        append("name", name)
                        append("email", email)
                        append("password", password)
                        append("file", imageProfile, Headers.build {
                            append(HttpHeaders.ContentDisposition, "filename=\"${filename}\"")
                        })
                    }
                )
                setBody(requestBody)
            }.call.response.body<RegisterUserResponseDTO>()
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun login(body: LoginRequestDTO): RegisterUserResponseDTO {
        return try {
            client.post(CommonApi.USER_API_URL + "login") {
                setBody(body)
            }.call.response.body<RegisterUserResponseDTO>()
        } catch (e: Exception) {
            throw e
        }
    }

}
