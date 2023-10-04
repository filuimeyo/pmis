package com.example.nikakudirko.myapplication

class ArticleRepository {
    fun getAll():List<NewsArticle>{
        return listOf(
            NewsArticle(
                "Сегодня плохой день",
                "Я",
                true
            ),
            NewsArticle(
                "Хочу избавиться от своего ребенка, задавайте вопросы",
                "Мама",
                false
            ),
            NewsArticle(
                "kzkzkzkkz",
                "Я",
                false
            ),
            NewsArticle(
                "отсотсоутол",
                "Мама",
                true
            ),
            NewsArticle(
                "чьчььььььььььььььььььььь",
                "Мама",
                true
            )
        )
    }
}