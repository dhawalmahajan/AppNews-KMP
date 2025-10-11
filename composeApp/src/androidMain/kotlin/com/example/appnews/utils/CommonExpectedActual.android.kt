package com.example.appnews.utils

import java.util.UUID
import kotlin.uuid.ExperimentalUuidApi

actual fun getType(): Type {
    return Type.Mobile
}

@OptIn(ExperimentalUuidApi::class)
actual fun getRandomId(): String {
    return UUID.randomUUID().toString()
}