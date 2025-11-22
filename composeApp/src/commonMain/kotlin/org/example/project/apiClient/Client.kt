package org.example.project.apiClient

import io.ktor.client.*
import io.ktor.client.call.body
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.get
import io.ktor.client.plugins.cookies.*
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import org.example.project.model.ExtendedPost
import org.example.project.model.Post
import io.ktor.serialization.kotlinx.json.json
import org.example.project.model.Rating

object Client{
    val client: HttpClient = HttpClient(CIO) {
        install(ContentNegotiation){
            json()
        }
        install(HttpCookies)
    }


    suspend fun getPosts(): List<ExtendedPost> {
        val posts: List<ExtendedPost> = client.get("${SERVER_URL}/posts").body()
        return posts
    }

    suspend fun post(post: Post): Int {
        val response = client.post("${SERVER_URL}/posts") {
            setBody(post)
    }
        return response.status.value
    }

    suspend fun createRating(postId: Int, rating: Rating) {
        client.post("${SERVER_URL}/posts/${postId}/vote") {
            setBody(rating)
        }
    }
}
