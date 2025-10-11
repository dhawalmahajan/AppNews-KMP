package com.example.appnews

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform