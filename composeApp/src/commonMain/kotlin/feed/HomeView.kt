package feed

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.MailOutline
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import app.mistercooper.domain_shared_common.user.model.UserModel
import app.mistercooper.social.domain.home.model.PostModel
import app.mistercooper.ui.common.components.LoadingComponent
import coil3.ImageLoader
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.request.crossfade
import coil3.util.DebugLogger
import common.component.CommonScaffoldBottomBar
import common.navigation.NavigationRoute
import common.navigation.NavigationRoute.COMMENTS
import common.navigation.navigate
import feed.viewmodel.HomeViewModel
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.comment
import kotlinproject.composeapp.generated.resources.like
import kotlinproject.composeapp.generated.resources.post_comments_total
import kotlinproject.composeapp.generated.resources.post_like_people
import org.jetbrains.compose.resources.stringResource
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
                Column(modifier = Modifier.fillMaxSize()) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        ImageLoader
                            .Builder(LocalPlatformContext.current)
                            .crossfade(true)
                            .logger(DebugLogger()).build()
                        AsyncImage(
                            model = post.imageUrl,
                            contentDescription = "",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            UserComponent(post.user)
                        }
                    }
                    if (post.totalLikes > 0) {
                        Text(
                            text = stringResource(
                                Res.string.post_like_people,
                                post.totalLikes
                            ),
                            modifier = Modifier.padding(4.dp)
                        )
                    }
                    InteractionIconsComponent(
                        post = post,
                        publishLike = { id, like -> publishLike(id, like) },
                        showComments = { globalNavigator.navigate(COMMENTS, post.id) })
                    if (post.description?.isNotEmpty() == true) {
                        Text(
                            text = post.description!!,
                            modifier = Modifier.padding(horizontal = 4.dp)
                        )
                    }
                    if (post.totalComments > 0) {
                        Text(
                            text = stringResource(
                                Res.string.post_comments_total,
                                post.totalComments
                            ),
                            modifier = Modifier
                                .padding(4.dp)
                                .clickable {
                                    globalNavigator.navigate(COMMENTS, post.id)
                                },
                            style = MaterialTheme.typography.caption
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }

        }
    }
}

@Composable
fun UserComponent(userModel: UserModel) {
    app.mistercooper.ui.common.components.UserMiniatureComponent(userModel.imageProfileUrl.orEmpty())
    Text(
        text = userModel.userName.orEmpty(),
        style = MaterialTheme.typography.h6,
        modifier = Modifier.padding(8.dp)
    )
}

@Composable
fun InteractionIconsComponent(
    post: PostModel,
    publishLike: (id: Long, like: Boolean) -> Unit,
    showComments: () -> Unit
) {
    Row {
        Icon(
            imageVector = if (post.hasLiked) Icons.Rounded.Favorite else Icons.Rounded.FavoriteBorder,
            contentDescription = stringResource(Res.string.like),
            modifier = Modifier
                .padding(4.dp)
                .clickable {
                    publishLike(post.id, !post.hasLiked)
                }
        )
        Icon(
            Icons.Rounded.MailOutline,
            contentDescription = stringResource(Res.string.comment),
            modifier = Modifier
                .padding(4.dp)
                .clickable {
                    showComments()
                }
        )
    }
}