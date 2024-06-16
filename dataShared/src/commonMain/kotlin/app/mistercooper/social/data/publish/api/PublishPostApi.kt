package app.mistercooper.data.publish.remote.api

import com.jetbrains.kmpapp.remote.api.CommonApi
import io.ktor.client.HttpClient
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.forms.formData
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.util.date.getTimeMillis

interface PublishPostRemoteApi {
    suspend fun publish(
        text: String,
        file: ByteArray,
        userName: String
    )
}

class PublishPostRemoteApiImpl(private val client: HttpClient) : PublishPostRemoteApi {

    override suspend fun publish(
        text: String,
        file: ByteArray,
        userName: String
    ) {
        try {
            client.post(CommonApi.SOCIAL_API_URL + "upload") {
                val requestBody = MultiPartFormDataContent(
                    formData {
                        append("description", text)
                        append("file", file, Headers.build {
                            append(HttpHeaders.ContentDisposition, "filename=\"${userName + getTimeMillis()}\"")
                        })
                    }
                )
                setBody(requestBody)
            }
            return
        } catch (e: Exception) {
            throw e
        }
    }
}