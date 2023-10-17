package com.example.nikakudirko.myapplication.viewmodels

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nikakudirko.myapplication.MemoriesDestinationsArgs.MEMORY_ID_ARG
import com.example.nikakudirko.myapplication.NewsArticle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Date
import java.util.UUID

interface NewsArticleDataSource {

    fun getArticles(): Flow<List<NewsArticle>>
    fun getArticle(id: UUID?): Flow<NewsArticle?>

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

        GlobalScope.launch(Dispatchers.Default) {
            while (true) {
                _articleFlow.emit(newsArticles)
                delay(5000L)
            }
        }
        return _articleFlow.asSharedFlow().map { it.values.toList() }
    }

    /* override fun getArticles(): Flow<List<NewsArticle>> = flow{
         while (true){
             delay(5000L)
             emit(newsArticles.values.toList())
         }
     }*/
    override fun getArticle(id: UUID?): Flow<NewsArticle?> {

        GlobalScope.launch(Dispatchers.Default) {
            while (true) {
                _articleFlow.emit(newsArticles)
                delay(5000L)
            }
        }

        return _articleFlow.asSharedFlow().map { it[id] }
    }

    override suspend fun upsert(newsArticle: NewsArticle) {
        newsArticles[newsArticle.id] = newsArticle
    }

    override suspend fun delete(id: UUID) {
        newsArticles.remove(id)
    }
}

interface ArticlesRepository {
    fun getArticles(): Flow<List<NewsArticle>>
    fun getArticle(id: UUID?): Flow<NewsArticle?>

    suspend fun upsert(newsArticle: NewsArticle)
    suspend fun delete(id: UUID)
}

object ArticlesRepositoryImpl : ArticlesRepository {

    private val dataSource: NewsArticleDataSource = InMemoryNewsArticleDatasource

    override fun getArticles(): Flow<List<NewsArticle>> {
        return dataSource.getArticles()
    }


    override fun getArticle(id: UUID?): Flow<NewsArticle?> {

        return dataSource.getArticle(id)
    }

    override suspend fun upsert(newsArticle: NewsArticle) {
        dataSource.upsert(newsArticle)
    }

    override suspend fun delete(id: UUID) {
        dataSource.delete(id)
    }

}


sealed class WorkResult<out R> {
    data class Success<out T>(val data: T) : WorkResult<T>()
    data class Error(val exception: Exception) : WorkResult<Nothing>()
    object Loading : WorkResult<Nothing>()
}

sealed interface HomeState {
    object Loading : HomeState
    object Empty : HomeState
    data class DisplayingCats(val cats: List<NewsArticle>) : HomeState
    data class Error(val e: Exception) : HomeState
}


data class ArticlesListUiState(
    val articles: List<NewsArticle> = emptyList(),
    val isLoading: Boolean = false,
    val isError: Boolean = false
)


data class ArticleUiState(
    val id: UUID = UUID.randomUUID(),
    val title: String = "",
    val author: String = "",
    val isDraft: Boolean = false,

    val isLoading: Boolean = false,
    val isArticleSaved: Boolean = false,
    val isArticleSaving: Boolean = false,
    val articleSavingError: String? = null
) {
    val articlesDate = Date()
}

/*class EditViewModel: ViewModel(
    val id: String?
){

    private  val ARTICLE_ID_ARG = "id"

    private val repository: ArticlesRepository = ArticlesRepositoryImpl
    private val savedStateHandle: SavedStateHandle = SavedStateHandle()

    private var articleId: String? = savedStateHandle[ARTICLE_ID_ARG]



}*/

class EditViewModel(
    //val id: String?,
    savedStateHandle: SavedStateHandle

) : ViewModel() {

    private val repository: ArticlesRepository = ArticlesRepositoryImpl

    //private val articles = repository.getArticle(UUID.fromString(id))
    private var articleId: String? = savedStateHandle[MEMORY_ID_ARG]

    private val _uiState = MutableStateFlow(ArticleUiState())
    val uiState: StateFlow<ArticleUiState> = _uiState.asStateFlow()

    init {
        if (articleId != null) {
            loadArticle(UUID.fromString(articleId))
        }
    }

    private fun loadArticle(articleId: UUID?) {

        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            val result = repository.getArticle(articleId).first()
            if (result == null) {
                _uiState.update { it.copy(isLoading = false) }
            } else {
                val article = result
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        title = article.title,
                        author = article.author,
                        isDraft = article.isDraft
                    )
                }
            }
        }


    }


    fun saveArticle() {
        viewModelScope.launch {
            try {
                _uiState.update { it.copy(isArticleSaving = true) }
                System.out.println(_uiState.value.title)
                if (articleId != null) {
                    repository.upsert(
                        NewsArticle(
                            id = UUID.fromString(articleId),
                            title = _uiState.value.title,
                            author = _uiState.value.author,
                            isDraft = _uiState.value.isDraft,
                        )
                    )
                } else {
                    repository.upsert(
                        NewsArticle(
                            title = _uiState.value.title,
                            author = _uiState.value.author,
                            isDraft = _uiState.value.isDraft,
                        )
                    )
                }
                _uiState.update{it.copy(isArticleSaved = true)}

            } catch (e: Exception){
                _uiState.update { it.copy(articleSavingError = "unable to save or edit article") }
            } finally {
                _uiState.update { it.copy(isArticleSaving = false) }
            }

        }


    }

    fun deleteArticle() {
        viewModelScope.launch {
            try {
                _uiState.update { it.copy(isArticleSaving = true) }
                System.out.println(articleId.toString())
                if(articleId!=null) {
                    repository.delete(UUID.fromString(articleId))
                }
                _uiState.update { it.copy(isArticleSaved = true) }
            }
            catch (e: Exception) {
                System.out.println(e)
                _uiState.update { it.copy(articleSavingError = "erorrrr") }
            }
            finally {
                _uiState.update { it.copy(isArticleSaving  = false) }
            }
            // withLoading {
            // }
        }
    }



    fun setArticleTitle(title: String) {
        _uiState.update { it.copy(title = title) }
    }

    fun setArticleAuthor(author: String) {
        _uiState.update { it.copy(author = author) }
    }

    fun setArticleDraft(isDraft: Boolean) {
        _uiState.update { it.copy(isDraft = isDraft) }
    }

}


class HomeViewModel : ViewModel() {

    private val repository: ArticlesRepository = ArticlesRepositoryImpl
    private val articles = repository.getArticles()

    private val articleLoadingItems = MutableStateFlow(0)
    val uiState = combine(articles, articleLoadingItems) { articles, loadingItems ->
        ArticlesListUiState(
            articles = articles.toList(),
            isLoading = loadingItems > 0,
            isError = false

        )
        /*when(articles){
            is WorkResult.Error -> ArticlesListUiState(isError = true)
            is WorkResult.Loading -> ArticlesListUiState(isLoading = true)
            is WorkResult.Success -> ArticlesListUiState(articles = articles.data, isLoading = loadingItems > 0)
        }*/
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = ArticlesListUiState(isLoading = true)
    )

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

    val items: SnapshotStateList<NewsArticle> = DefaultArticles.toMutableStateList()


    private suspend fun withLoading(block: suspend () -> Unit) {
        try {
            addLoadingElement()
            block()
        }
        finally {
            removeLoadingElement()
        }
    }

    private fun addLoadingElement() = articleLoadingItems.getAndUpdate { num -> num + 1 }
    private fun removeLoadingElement() = articleLoadingItems.getAndUpdate { num -> num - 1 }
    fun deleteAticle(articleId: UUID){
        viewModelScope.launch {

            withLoading { repository.delete(articleId) }
        }
    }
    fun onClickRemoveArticle(article: NewsArticle) = items.remove(article)

}

