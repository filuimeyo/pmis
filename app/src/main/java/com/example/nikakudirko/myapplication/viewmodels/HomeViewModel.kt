package com.example.nikakudirko.myapplication.viewmodels

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.example.nikakudirko.myapplication.NewsArticle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import java.util.UUID

interface NewsArticleDataSource {

    fun getArticles(): Flow<List<NewsArticle>>
    fun getArticle(id: UUID): Flow<NewsArticle?>

    suspend fun upsert(newsArticle: NewsArticle)
    suspend fun delete(id: UUID)
}

object InMemoryNewsArticleDatasource : NewsArticleDataSource {

  //  private val newsArticles = DefaultArticles.associateBy { it.id }.toMutableMap() // (1)

    private val _articleFlow = MutableSharedFlow<Map<UUID, NewsArticle>>(1) // (2)
    override fun getArticles(): Flow<List<NewsArticle>> {
        TODO("Not yet implemented")
    }

    override fun getArticle(id: UUID): Flow<NewsArticle?> {
        TODO("Not yet implemented")
    }

    override suspend fun upsert(newsArticle: NewsArticle) {
        TODO("Not yet implemented")
    }

    override suspend fun delete(id: UUID) {
        TODO("Not yet implemented")
    }

}


class HomeViewModel() : ViewModel() {

    val items: SnapshotStateList<NewsArticle> = DefaultArticles.toMutableStateList()


    fun onClickRemoveArticle(article: NewsArticle) = items.remove(article)


    private companion object {

        private val DefaultArticles = listOf(
            NewsArticle(
                title = "Мажу это на колено 3 день, прошла шишка....",
                author = "Дундер Никита",
                isDraft = true,

                ),
            NewsArticle(
                title = "Закрой рот. Именно так сказал известный программист...",
                author = "Дундер Никита",
                isDraft = true,

                ),
            NewsArticle(
                title = "Дундер Никита: сеньор с 5 лет",
                author = "Дундер Никита",
                isDraft = false,
                ),
            NewsArticle(
                title = "Дундер Никита продал гит за сосиску в тесте",
                author = "Я",
                isDraft = false,

                ),

        )
    }

}