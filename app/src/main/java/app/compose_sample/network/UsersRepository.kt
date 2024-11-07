package app.compose_sample.network

import app.compose_sample.data.Post
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class UsersRepository @Inject constructor(private val apiService: ApiService) {

    fun getPosts(): Flow<List<Post>> = flow {
        emit(apiService.getPosts())
    }.flowOn(Dispatchers.IO)

}