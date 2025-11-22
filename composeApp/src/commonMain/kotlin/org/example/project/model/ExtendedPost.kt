package org.example.project.model

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class ExtendedPost(
    val title: String,
    val description: String,
    val createdAt: LocalDateTime,
    val upvoteCount: Int,
    val downvoteCount: Int,
    val ownRating: Int?
)
