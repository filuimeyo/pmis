package com.example.nikakudirko.myapplication

class ArticleRepository {
    fun getAll():List<NewsArticle>{
        return listOf(
            NewsArticle(
                title = "Сегодня плохой день",
                author = "Я",
                isDraft = true,

            ),
            NewsArticle(
                title = "Сегодня плохой день",
                author = "Я",
                isDraft = true,

                ),
        )
    }
}