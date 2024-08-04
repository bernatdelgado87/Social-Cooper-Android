package feed

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import app.mistercooper.social.domain.home.model.PostModel
import app.mistercooper.ui.common.components.LoadingComponent
import common.component.CommonScaffoldBottomBar
import common.component.post.PostComponent
import common.navigation.NavigationRoute
import common.navigation.navigate
import feed.viewmodel.HomeViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen(globalNavigator: NavController) {
    val viewModel: HomeViewModel = koinViewModel()
    val state = viewModel.homeUiModel.collectAsState()

    CommonScaffoldBottomBar(
        content = { modifier ->
            HomeFeedView(
                modifier = modifier,
                globalNavigator = globalNavigator,
                postModels = state.value.postModels,
                publishLike = { id: Long, like: Boolean -> viewModel.publishLike(id, like) }
            )
            if (state.value.isLoading) {
                LoadingComponent()
            }
        },
        showError = state.value.isError,
        actionFloatingButton = {
            globalNavigator.navigate(NavigationRoute.PUBLISH_NOW)
        },
        iconVectorFloatingButton = Icons.Rounded.Add
    )
}

@Composable
fun HomeFeedView(
    postModels: List<PostModel>?,
    globalNavigator: NavController,
    modifier: Modifier = Modifier,
    publishLike: (id: Long, like: Boolean) -> Unit
) {
    postModels?.let { posts ->
        LazyColumn(modifier) {
            items(posts) { post ->
                    val state = rememberLazyListState()
                    PostComponent(post, globalNavigator) { id, like -> publishLike(id, like) }
            }
        }
    }


}