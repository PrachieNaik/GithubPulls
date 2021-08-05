package com.example.githubpulls.data

data class Pull(
    val title: String,
    val user: User,
    val created_at: String,
    val closed_at: String
)

data class User(
    val login: String,
    val avatar_url: String
)