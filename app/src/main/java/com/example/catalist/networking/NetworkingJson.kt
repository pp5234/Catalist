package com.example.catalist.networking

import kotlinx.serialization.json.Json

val NetworkingJson = Json {
    ignoreUnknownKeys = true
    prettyPrint = true
}