package org.example.socialsync.auth.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.bson.types.ObjectId


@Serializable
data class User(
    @SerialName("_id") val id: String = ObjectId().toString(),
    val username: String,
    val password: String,
    val salt: String
)