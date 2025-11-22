package org.example.project.apiClient

import io.ktor.client.*
import io.ktor.client.call.body
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import org.example.project.model.ExtendedPost

class Client{
    val client: HttpClient = HttpClient(CIO) {
        install(ContentNegotiation){
            json()
        }
    }

    suspend fun getPosts(): List<ExtendedPost> {
        val posts: List<ExtendedPost> = client.get("${SERVER_URL}/posts").body()
        return posts
    }


}