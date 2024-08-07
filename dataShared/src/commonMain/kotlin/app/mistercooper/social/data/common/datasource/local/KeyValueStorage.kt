package com.jetbrains.kmpapp.local

import com.russhwolf.settings.Settings
import com.russhwolf.settings.get
import com.russhwolf.settings.set

interface KeyValueStorageInterface {
    var token: String?
    fun cleanStorage()

}

enum class StorageKeys {
    TOKEN;
    val key get() = this.name

}

class KeyValueStorageImpl : KeyValueStorageInterface {
    private val settings: Settings by lazy { Settings() }

    // #1 - store/retrive primitive type
    override var token: String?
        get() = settings[StorageKeys.TOKEN.key]
        set(value) {
            settings[StorageKeys.TOKEN.key] = value
        }

    override fun cleanStorage() {
        settings.clear()
    }
}