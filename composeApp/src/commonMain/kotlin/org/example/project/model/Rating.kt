package org.example.project.model


import kotlinx.serialization.Serializable

@Serializable
data class Rating(
    val post: Int,
    val rating: Int
) {}