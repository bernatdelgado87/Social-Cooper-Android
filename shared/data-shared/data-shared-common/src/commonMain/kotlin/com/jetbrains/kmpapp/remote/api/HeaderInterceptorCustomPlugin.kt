package com.jetbrains.kmpapp.remote.api

import com.jetbrains.kmpapp.local.KeyValueStorageInterface
import io.ktor.client.plugins.api.ClientPlugin
import io.ktor.client.plugins.api.createClientPlugin

fun headerInterceptorCustomPlugin(keyValueStorageInterface: KeyValueStorageInterface): ClientPlugin<Unit> {
    return createClientPlugin("CustomHeaderPlugin") {
        onRequest { request, _ ->
            if (!keyValueStorageInterface.token.isNullOrBlank()) {
                request.headers.append("auth-token", keyValueStorageInterface.token!!)
            }
        }
    }
}