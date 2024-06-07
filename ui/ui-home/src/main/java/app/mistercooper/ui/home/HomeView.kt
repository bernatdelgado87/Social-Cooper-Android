package app.mistercooper.ui.home

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.MailOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import app.mistercooper.domain.home.model.PostModel
import app.mistercooper.domain_shared_common.user.model.UserModel
import app.mistercooper.ui.common.components.CommonScaffoldBottomBar
import app.mistercooper.ui.common.components.LoadingComponent
import app.mistercooper.ui.common.navigation.ArgumentNavigatorWrapper
import app.mistercooper.ui.common.navigation.BottomSheetRoute
import app.mistercooper.ui.common.navigation.CommentNavigationArgs.Companion.ON_DISMISS_KEY
import app.mistercooper.ui.common.navigation.CommentNavigationArgs.Companion.POST_ID_KEY
import app.mistercooper.ui.common.navigation.CommentNavigationArgs.Companion.WRITE_NOW_KEY
import app.mistercooper.ui.common.navigation.GlobalNavigator
import app.mistercooper.ui.common.navigation.NavigationRoute
import app.mistercooper.ui.common.navigation.navigate
import app.mistercooper.ui_home_shared.viewmodel.HomeViewModel
import coil.compose.AsyncImage
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(globalNavigator: GlobalNavigator) {
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
            globalNavigator.nativeController.navigate(NavigationRoute.PUBLISH_NOW)
        },
        iconVectorFloatingButton = Icons.Rounded.Add
    )
}

@Composable
fun HomeFeedView(
    postModels: List<PostModel>?,
    globalNavigator: GlobalNavigator,
    modifier: Modifier = Modifier,
    publishLike: (id: Long, like: Boolean) -> Unit
) {
    postModels?.let { posts ->
        LazyColumn(modifier) {
            items(posts) { post ->
                Column(modifier = Modifier.fillMaxSize()) {
                    Box(modifier = Modifier.fillMaxSize()) {
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
                                id = R.string.post_like_people,
                                post.totalLikes
                            ),
                            modifier = Modifier.padding(4.dp)
                        )
                    }
                    InteractionIconsComponent(
                        globalNavigator,
                        post,
                        { id, like -> publishLike(id, like) })
                    if (post.description?.isNotEmpty() == true) {
                        Text(
                            text = post.description!!,
                            modifier = Modifier.padding(horizontal = 4.dp)
                        )
                    }
                    if (post.totalComments > 0) {
                        var showComments: Boolean by remember { mutableStateOf(false) }
                        if (showComments) {
                            globalNavigator.customNavigator.showBottomSheet(
                                BottomSheetRoute.COMMENTS,
                                mapOf(POST_ID_KEY to ArgumentNavigatorWrapper.LongArg(post.id),
                                    WRITE_NOW_KEY to ArgumentNavigatorWrapper.BooleanArg(false),
                                    ON_DISMISS_KEY to ArgumentNavigatorWrapper.FunctionArg {
                                        showComments = false
                                    })
                            )
                        }
                        Text(
                            text = stringResource(
                                id = R.string.post_comments_total,
                                post.totalComments
                            ),
                            modifier = Modifier
                                .padding(4.dp)
                                .clickable {
                                    showComments = true
                                },
                            style = MaterialTheme.typography.labelLarge
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
        style = MaterialTheme.typography.headlineSmall,
        modifier = Modifier.padding(8.dp)
    )
}

@Composable
fun InteractionIconsComponent(
    globalNavigator: GlobalNavigator,
    post: PostModel,
    publishLike: (id: Long, like: Boolean) -> Unit
) {
    Row {
        var showComments: Boolean by remember { mutableStateOf(false) }
        Icon(
            imageVector = if (post.hasLiked) Icons.Rounded.Favorite else Icons.Rounded.FavoriteBorder,
            contentDescription = stringResource(id = R.string.like),
            modifier = Modifier
                .padding(4.dp)
                .clickable {
                    publishLike(post.id, !post.hasLiked)
                }
        )
        Icon(
            Icons.Rounded.MailOutline,
            contentDescription = stringResource(id = R.string.comment),
            modifier = Modifier
                .padding(4.dp)
                .clickable {
                    showComments = true
                }
        )
        if (showComments) {
            globalNavigator.customNavigator.showBottomSheet(
                BottomSheetRoute.COMMENTS,
                mapOf(POST_ID_KEY to ArgumentNavigatorWrapper.LongArg(post.id),
                    WRITE_NOW_KEY to ArgumentNavigatorWrapper.BooleanArg(true),
                    ON_DISMISS_KEY to ArgumentNavigatorWrapper.FunctionArg {
                        showComments = false
                    })
            )
        }
    }
}