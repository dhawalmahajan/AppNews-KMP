package com.example.appnews.utils
import kotlin.js.*
actual fun getType(): Type {
    return Type.Web
}


@OptIn(ExperimentalWasmJsInterop::class)
actual fun getRandomId(): String = js("crypto.randomUUID()") as String