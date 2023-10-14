package com.example.nikakudirko.myapplication


import java.util.Date
import java.util.UUID

data class NewsArticle(
    val id: UUID = UUID.randomUUID(),
    val title: String,
    val author: String,
    val isDraft: Boolean,
) {
    val articlesDate = Date()
}