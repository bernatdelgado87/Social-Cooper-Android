package app.mistercooper.data.publish.remote.api

import com.jetbrains.kmpapp.remote.api.CommonApi
import io.ktor.client.HttpClient
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.forms.formData
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import java.io.File

interface PublishPostRemoteApi {
    suspend fun publish(
        text: String,
        file: File
    )
}

class PublishPostRemoteApiImpl(private val client: HttpClient) : PublishPostRemoteApi {

    override suspend fun publish(
        text: String,
        file: File
    ) {
        try {
            client.post(CommonApi.SOCIAL_API_URL + "upload") {
                val requestBody = MultiPartFormDataContent(
                    formData {
                        append("description", text)
                        append("file", file.readBytes(), Headers.build {
                            append(HttpHeaders.ContentDisposition, "filename=\"${file.name}\"")
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