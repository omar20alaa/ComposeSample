package app.compose_sample.util

import app.compose_sample.data.Post

sealed class ApiState {

    class Success(val data: List<Post>) : ApiState()
    class Failure(val msg: Throwable) : ApiState()
    data object Loading : ApiState()
    data object Empty : ApiState()

}