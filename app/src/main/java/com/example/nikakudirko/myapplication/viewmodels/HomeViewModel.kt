package com.example.nikakudirko.myapplication.viewmodels

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.example.nikakudirko.myapplication.NewsArticle
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import java.util.UUID

interface NewsArticleDataSource {

    fun getArticles(): Flow<List<NewsArticle>>
    fun getArticle(id: UUID): Flow<NewsArticle?>

    suspend fun upsert(newsArticle: NewsArticle)
    suspend fun delete(id: UUID)
}

object InMemoryNewsArticleDatasource : NewsArticleDataSource {

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

    private val newsArticles = DefaultArticles.associateBy { it.id }.toMutableMap() // (1)

    private val _articleFlow = MutableSharedFlow<Map<UUID, NewsArticle>>(1) // (2)
    override fun getArticles(): Flow<List<NewsArticle>> {
        return _articleFlow.asSharedFlow().map{ it.values.toList() }
    }

   /* override fun getArticles(): Flow<List<NewsArticle>> = flow{
        while (true){
            delay(500000L)
            emit(newsArticles.values.toList())
        }
    }
*/
    override fun getArticle(id: UUID): Flow<NewsArticle?> {
        return _articleFlow.asSharedFlow().map { it[id] }
    }

    override suspend fun upsert(newsArticle: NewsArticle) {
        newsArticles[newsArticle.id] = newsArticle
        //??
    }

    override suspend fun delete(id: UUID) {
       newsArticles.remove(id)
        //??
    }
}

interface ArticlesRepository {
    fun getArticles(): Flow<List<NewsArticle>>
    fun getArticle(id: UUID): Flow<NewsArticle?>

    suspend fun upsert(newsArticle: NewsArticle)
    suspend fun delete(id: UUID)
}

object ArticlesRepositoryImpl : ArticlesRepository {

    private val dataSource: NewsArticleDataSource = InMemoryNewsArticleDatasource

    override fun getArticles(): Flow<List<NewsArticle>> {
        return  dataSource.getArticles()
    }

    override fun getArticle(id: UUID): Flow<NewsArticle?> {
        return dataSource.getArticle(id)
    }

    override suspend fun upsert(newsArticle: NewsArticle) {
        dataSource.upsert(newsArticle)
    }

    override suspend fun delete(id: UUID) {
        dataSource.delete(id)
    }

}

sealed interface HomeState {
    object Loading : HomeState
    //data
    object Empty: HomeState
    data class DisplayingArticles(val articles: List<NewsArticle>): HomeState
    data class Error(val e: Exception) : HomeState
}


class HomeViewModel() : ViewModel() {




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

    val repository: ArticlesRepository = ArticlesRepositoryImpl




    val items: SnapshotStateList<NewsArticle> = DefaultArticles.toMutableStateList()


    fun onClickRemoveArticle(article: NewsArticle) = items.remove(article)

}