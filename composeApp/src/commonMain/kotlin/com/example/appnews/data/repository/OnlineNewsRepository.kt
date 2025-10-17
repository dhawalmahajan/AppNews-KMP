package com.example.appnews.data.repository

import com.example.appnews.BuildKonfig
import com.example.appnews.utils.BASE_URL
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class OnlineNewsRepository {
    private val httpClient = HttpClient {
        defaultRequest {
            url(BASE_URL)
            contentType(ContentType.Application.Json)
        }
        install(HttpTimeout) {
            requestTimeoutMillis = 60_0000
        }
        install(ContentNegotiation) {
            json(
                Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                    explicitNulls = false
                }
            )
        }
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
            logger = object : Logger {
                override fun log(message: String) {
                    println("Ktor Client: $message")
                    co.touchlab.kermit.Logger.d("Ktor Client") {
                        message
                    }
                }
            }
        }
    }

    suspend fun getNews(category: String): HttpResponse {
        return httpClient.get {
            url("top-headlines")
            parameter("country", "us")
            parameter("category", category)
            parameter("apiKey", BuildKonfig.API_KEY)
        }
    }

    suspend fun searchNews(query: String): HttpResponse {
        return httpClient.get {
            url("everything")
            parameter("q", query)
            parameter("apiKey", BuildKonfig.API_KEY)
        }
    }


}