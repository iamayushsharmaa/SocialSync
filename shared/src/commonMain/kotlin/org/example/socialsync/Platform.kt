package org.example.socialsync

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform