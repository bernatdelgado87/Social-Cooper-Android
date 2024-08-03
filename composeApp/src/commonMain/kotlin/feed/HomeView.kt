package feed

import StandarDialog
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.AlertDialog
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
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
    var openAlertDialog = remember { mutableStateOf(false) }

    if (openAlertDialog.value) {
        StandarDialog(
            onDismissRequest = { openAlertDialog.value = false },
            onConfirmation = {
                globalNavigator.navigate(NavigationRoute.REGISTER)
                openAlertDialog.value = false
                             },
            dialogTitle = "Alert dialog example",
            dialogText = "This is an example of an alert dialog with buttons."
        )
    }
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
            if (state.value.isFullRegistered == true) {
                globalNavigator.navigate(NavigationRoute.PUBLISH_NOW)
            } else {
                openAlertDialog.value = true
            }
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
                Column(modifier = Modifier.fillMaxSize()) {
                    PostComponent(post, globalNavigator) { id, like -> publishLike(id, like) }
                }

            }
        }
    }


}