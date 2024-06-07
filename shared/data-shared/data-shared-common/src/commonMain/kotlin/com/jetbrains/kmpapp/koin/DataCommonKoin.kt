package app.mistercooper.data.comment.koin

import com.jetbrains.kmpapp.local.KeyValueStorageImpl
import com.jetbrains.kmpapp.local.KeyValueStorageInterface
import com.jetbrains.kmpapp.remote.api.createKtorClient
import io.ktor.client.HttpClient
import org.koin.dsl.module

val commonDataModule = module {
    single<HttpClient> { createKtorClient(get()) }
    single<KeyValueStorageInterface> { KeyValueStorageImpl() }
}

