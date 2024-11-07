package app.compose_sample.ui.users

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.compose_sample.data.Post
import app.compose_sample.util.ApiState

@Composable
private fun EachRow(post: Post) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 8.dp,
                vertical = 8.dp
            ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(4.dp)
    ) {
        Text(text = post.body,
            modifier = Modifier.padding(10.dp))
    }
}


@Composable
private fun FetchData(usersViewModel: UsersViewModel) =
    when (val result = usersViewModel.response.value) {
        is ApiState.Success -> {
            LazyColumn {
                items(result.data) { response ->
                    EachRow(post = response)
                }
            }
        }

        is ApiState.Failure -> {
            Text(text = "${result.msg}")
        }

        is ApiState.Loading -> {
            CircularProgressIndicator()
        }

        is ApiState.Empty -> {
            Text(text = "No Data",
                modifier = Modifier.padding(10.dp))
        }

}