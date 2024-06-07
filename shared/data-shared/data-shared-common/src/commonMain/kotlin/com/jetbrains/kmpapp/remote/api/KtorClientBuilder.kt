package com.jetbrains.kmpapp.remote.api

import com.jetbrains.kmpapp.local.KeyValueStorageInterface
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json


fun createKtorClient(keyValueStorageInterface: KeyValueStorageInterface): HttpClient {
    val json = Json { ignoreUnknownKeys = true }
    return HttpClient {
        defaultRequest {
            header(HttpHeaders.ContentType, ContentType.Application.Json)
        }
        install(ContentNegotiation) {
            json(json, contentType = ContentType.Application.Json)
        }
        install(headerInterceptorCustomPlugin(keyValueStorageInterface))
    }
}