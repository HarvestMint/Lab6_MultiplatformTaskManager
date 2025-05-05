package dev.mintyverse.lab6

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform