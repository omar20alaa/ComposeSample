package app.compose_sample.network

import app.compose_sample.data.Post
import retrofit2.http.GET

interface ApiService {

    companion object {
        const val BASE_URL = "https://jsonplaceholder.typicode.com/"
    }


    @GET("posts")
    suspend fun getPosts(): List<Post>


}