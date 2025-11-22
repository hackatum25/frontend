package org.example.project.model

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class Post(
    val title: String,
    val description: String,
)
