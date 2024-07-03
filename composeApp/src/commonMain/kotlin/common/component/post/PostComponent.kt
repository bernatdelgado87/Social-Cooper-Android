package common.component.post

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.MailOutline
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import app.mistercooper.domain_shared_common.user.model.UserModel
import app.mistercooper.social.domain.home.model.PostModel
import coil3.ImageLoader
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.request.crossfade
import coil3.util.DebugLogger
import common.navigation.NavigationRoute.COMMENTS
import common.navigation.navigate
import common.replaceParams
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.comment
import kotlinproject.composeapp.generated.resources.img_placeholder_profile
import kotlinproject.composeapp.generated.resources.like
import kotlinproject.composeapp.generated.resources.post_comments_total
import kotlinproject.composeapp.generated.resources.post_like_people
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource


@Composable
fun PostComponent(post: PostModel, globalNavigator: NavController, modifier: Modifier = Modifier, publishLike: ((id: Long, like: Boolean) -> Unit)? = null) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        UserComponent(post.user)
    }
    ImageLoader
        .Builder(LocalPlatformContext.current)
        .crossfade(true)
        .logger(DebugLogger()).build()
    AsyncImage(
        placeholder = painterResource(Res.drawable.img_placeholder_profile),
        model = post.imageUrl,
        contentDescription = "",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
    )
    publishLike?.let {
        if (post.totalLikes > 0) {
            Text(
                text = stringResource(
                    Res.string.post_like_people,
                    post.totalLikes
                ).replaceParams(post.totalLikes),
                modifier = Modifier.padding(start = 16.dp, top = 8.dp),
                style = MaterialTheme.typography.body2
            )
        }
        InteractionIconsComponent(
            post = post,
            publishLike = { id, like -> publishLike(id, like) },
            showComments = { globalNavigator.navigate(COMMENTS, post.id) })
    }
    if (post.description?.isNotEmpty() == true) {
        Text(
            text = post.description!!,
            modifier = Modifier.padding(horizontal = 16.dp),
            style = MaterialTheme.typography.body1
        )
    }
    publishLike?.let {
        if (post.totalComments > 0) {
            Text(
                text = stringResource(
                    Res.string.post_comments_total,
                    post.totalComments
                ).replaceParams(post.totalComments),
                modifier = Modifier
                    .padding(vertical = 4.dp, horizontal = 16.dp)
                    .clickable {
                        globalNavigator.navigate(COMMENTS, post.id)
                    },
                style = MaterialTheme.typography.caption
            )
        }
    }
    Spacer(modifier = Modifier.height(20.dp))
}

@Composable
private fun UserComponent(userModel: UserModel) {
    app.mistercooper.ui.common.components.UserMiniatureComponent(userModel.imageProfileUrl.orEmpty())
    Text(
        text = userModel.userName.orEmpty(),
        style = MaterialTheme.typography.h6,
        modifier = Modifier.padding(8.dp)
    )
}

@Composable
private fun InteractionIconsComponent(
    post: PostModel,
    publishLike: (id: Long, like: Boolean) -> Unit,
    showComments: () -> Unit
) {
    Row(
        modifier = Modifier.padding(start = 16.dp),

    ) {
        Icon(
            imageVector = if (post.hasLiked) Icons.Rounded.Favorite else Icons.Rounded.FavoriteBorder,
            contentDescription = stringResource(Res.string.like),
            modifier = Modifier
                .padding(top = 4.dp, bottom = 4.dp, end = 8.dp)
                .clickable {
                    publishLike(post.id, !post.hasLiked)
                }
        )
        Icon(
            Icons.Rounded.MailOutline,
            contentDescription = stringResource(Res.string.comment),
            modifier = Modifier
                .padding(vertical = 4.dp)
                .clickable {
                    showComments()
                }
        )
    }
}