package app.mistercooper.social.data.user.remote.api

import app.mistercooper.data.register_login.remote.dto.request.LoginRequestDTO
import app.mistercooper.social.data.user.remote.dto.response.RegisterUserResponseDTO
import app.mistercooper.social.data.user.remote.dto.response.UpdateUserResponseDTO
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
        apikey: String, name: String?, email: String?, password: String?, imageProfile: ByteArray?, filename: String?
    ): RegisterUserResponseDTO

    suspend fun login(
        body: LoginRequestDTO
    ): RegisterUserResponseDTO

    suspend fun updateUser(
        name: String?, email: String?, password: String?, imageProfile: ByteArray?, filename: String?
    ): UpdateUserResponseDTO

}

class RegisterLoginApiImpl(private val client: HttpClient) : RegisterLoginApi{
    override suspend fun registerUser(
        apikey: String,
        name: String?,
        email: String?,
        password: String?,
        imageProfile: ByteArray?,
        filename: String?
    ): RegisterUserResponseDTO {
        return try {

            client.post(CommonApi.USER_API_URL + "register") {
                val requestBody = MultiPartFormDataContent(
                    formData {
                        apikey?.let { append("apikey", apikey) }
                        name?.let { append("name", name) }
                        email?.let { append("email", email) }
                        password?.let { append("password", password) }
                        imageProfile?.let { append("file", imageProfile, Headers.build {
                            append(HttpHeaders.ContentDisposition, "filename=\"${filename}\"")
                        }) }
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

    override suspend fun updateUser(
        name: String?,
        email: String?,
        password: String?,
        imageProfile: ByteArray?,
        filename: String?
    ): UpdateUserResponseDTO {
        return try {
            client.post(CommonApi.USER_API_URL + "update") {
                val requestBody = MultiPartFormDataContent(
                    formData {
                        name?.let { append("name", name) }
                        email?.let { append("email", email) }
                        password?.let { append("password", password) }
                        imageProfile?.let { append("file", imageProfile, Headers.build {
                            append(HttpHeaders.ContentDisposition, "filename=\"${filename}\"")
                        }) }
                    }
                )
                setBody(requestBody)
            }.call.response.body<UpdateUserResponseDTO>()
        } catch (e: Exception) {
            throw e
        }
    }

}
