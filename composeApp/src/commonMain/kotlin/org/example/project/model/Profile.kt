package org.example.project.model

import kotlinx.serialization.Serializable

@Serializable
class Profile (
    val username: String,
    val firstname: String,
    val lastname: String,
    )