package id.ac.amikom.appstore.app.data.repository

import id.ac.amikom.appstore.app.data.model.ActionState
import id.ac.amikom.appstore.app.data.model.News
import id.ac.amikom.appstore.app.data.remote.NewsServis
import id.ac.amikom.appstore.app.data.remote.RetrofitApi
import retrofit2.await

class NewsRepository {
    private val newsServis: NewsServis by lazy { RetrofitApi.newsServis() }

    suspend fun listNews(): ActionState<List<News>> {
        return try {
            val list = newsServis.listNews().await()
            ActionState(list.data)
        } catch (e: Exception) {
            ActionState(message = e.message, isSucces = false)
        }
    }

    suspend fun detailNews(url: String): ActionState<News> {
        return try {
            val list = newsServis.detailNews(url).await()
            ActionState(list.data.first())
        } catch (e: Exception) {
            ActionState(message = e.message, isSucces = false)
        }
    }

    suspend fun searchNews(query: String): ActionState<List<News>> {
       return try {
            val list = newsServis.searchNews(query).await()
            ActionState(list.data)
        } catch (e: Exception) {
            ActionState(message = e.message, isSucces = false)
        }
    }
}
