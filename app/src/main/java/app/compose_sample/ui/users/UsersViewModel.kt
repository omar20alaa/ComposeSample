package app.compose_sample.ui.users

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.compose_sample.network.UsersRepository
import app.compose_sample.util.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class UsersViewModel @Inject constructor(private val usersRepository: UsersRepository) : ViewModel() {

    val response: MutableState<ApiState> = mutableStateOf(ApiState.Empty)

    init {
        getPost()
    }

    private fun getPost() =
        viewModelScope.launch {
            usersRepository.getPosts().onStart {
                response.value = ApiState.Loading
            }.catch {
                response.value = ApiState.Failure(it)
            }.collect {
                response.value = ApiState.Success(it)
            }
        }

}