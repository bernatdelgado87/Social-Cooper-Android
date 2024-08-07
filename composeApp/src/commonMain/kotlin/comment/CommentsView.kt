package comment

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Send
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import app.mistercooper.social.domain.comment.model.CommentWrapperModel
import app.mistercooper.ui.common.components.UserMiniatureComponent
import app.mistercooper.ui_comment_shared.model.PublishCommentUiModel
import comment.viewmodel.CommentViewModel
import common.component.CommonScaffoldTopBar
import common.component.CustomTextField
import common.component.post.PostComponent
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.comment
import kotlinproject.composeapp.generated.resources.comment_hint
import kotlinproject.composeapp.generated.resources.comments_empty_body
import kotlinproject.composeapp.generated.resources.comments_empty_title
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CommentsScreen(
    postId: Long,
    navController: NavController
) {
    val viewModel: CommentViewModel = koinViewModel()
    val viewModelState = viewModel.commentUiModel.collectAsState()
    if (viewModelState.value.commentWrapper == null){
        viewModel.getComments(postId)
    }
    CommonScaffoldTopBar(
        globalNavigator = navController,
        topBarTitle = stringResource(Res.string.comment),
        content = { modifier ->
            CommentsComponent(
                Modifier.imePadding(),
                postId,
                false,
                viewModelState.value,
                navController,
                { postId -> viewModel.getComments(postId) },
                { text, postId -> viewModel.publishComment(text, postId) },
                { viewModel.resetValue() })
        },
        showError = viewModelState.value.isError)
}

@Composable
fun CommentsComponent(modifier: Modifier, postId: Long, showKeyboard: Boolean = false, publishCommentUiModel: PublishCommentUiModel, navController: NavController, getComments: (postId: Long) -> Unit, publishComment: (text: String, postId: Long) -> Unit, resetValue: () -> Unit){
    Box(modifier = modifier.fillMaxSize()) {
        publishCommentUiModel.commentWrapper?.let {
            if (publishCommentUiModel.commentWrapper.comments.isNotEmpty()) {
                CommentsListComponent(commentWrapper = publishCommentUiModel.commentWrapper, navController) {
                    getComments(
                        postId
                    )
                }
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxHeight(0.8f)
                        .align(Alignment.Center)

                ) {
                    Text(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        text = stringResource(Res.string.comments_empty_title),
                        style = MaterialTheme.typography.h4,
                    )

                    Text(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(vertical = 20.dp),
                        text = stringResource(Res.string.comments_empty_body),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.h4,
                    )
                }
            }
        }
        CommentsFooterComponent(postId, showKeyboard, publishCommentUiModel, publishCommentUiModel.isPublished, { text, id ->
            publishComment(
                text,
                id,
            )
        },{ resetValue()})
    }
}


@Composable
fun CommentsListComponent(commentWrapper: CommentWrapperModel?, navController: NavController, getComments: () -> Unit) {
    if (commentWrapper == null) {
        getComments()
    } else {
        val lazyColumnListState = rememberLazyListState().apply {
            LaunchedEffect(commentWrapper.comments.size) {
                animateScrollToItem(commentWrapper.comments.size)
            }
        }
        LazyColumn(contentPadding = PaddingValues(bottom = 200.dp), state = lazyColumnListState) {
            item {
             PostComponent(commentWrapper.post, navController, modifier = Modifier.fillMaxHeight(0.5f))
            }
            items(commentWrapper.comments) { comment ->
                Row(modifier = Modifier.padding(vertical = 10.dp, horizontal = 16.dp)) {
                    UserMiniatureComponent(comment.user.imageProfileUrl.orEmpty())
                    Column {
                        Row {
                            Text(
                                modifier = Modifier
                                    .padding(horizontal = 8.dp)
                                    .align(Alignment.Bottom),
                                text = comment.user.userName.orEmpty(),
                                style = MaterialTheme.typography.h6
                            )
                            Text(
                                modifier = Modifier
                                    .padding(horizontal = 8.dp)
                                    .align(Alignment.Bottom),
                                text = comment.date,
                                style = MaterialTheme.typography.body2,
                                maxLines = 1
                            )
                        }
                        Row(
                            modifier = Modifier.padding(horizontal = 8.dp),
                        ) {
                            Text(
                                text = comment.text,
                                style = MaterialTheme.typography.body1
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BoxScope.CommentsFooterComponent(
    postId: Long,
    showKeyboard: Boolean,
    uiState: PublishCommentUiModel,
    resetText: Boolean,
    publishComment: (commentText: String, postId: Long) -> Unit,
    resetValue: () -> Unit
) {
    val focusManager = LocalFocusManager.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.BottomStart)
    ) {
        Box(
            modifier = Modifier
                .background(color = Color.Black)
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 40.dp)

        ) {
            Row(Modifier.fillMaxWidth()) {
                UserMiniatureComponent(uiState.commentWrapper?.myImageUrl.orEmpty())
                var commentText by remember { mutableStateOf("") }
                CustomTextField(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .padding(horizontal = 8.dp)
                        .height(40.dp),
                    onTextChanged = { newText -> commentText = newText },
                    showKeyboard,
                    stringResource(Res.string.comment_hint),
                    singleLine = false,
                    initialText = commentText
                )
                if (resetText){
                    commentText = ""
                    resetValue()
                }
                if (uiState.isLoadingPublish) {
                    AnimatedLoadingPublishComment()
                } else {
                    Icon(
                        Icons.AutoMirrored.Rounded.Send,
                        contentDescription = stringResource(Res.string.comment),
                        modifier = Modifier
                            .padding(4.dp)
                            .align(Alignment.CenterVertically)
                            .clickable {
                                focusManager.clearFocus()
                                publishComment(commentText, postId)
                            }
                    )
                }
            }
        }
    }
}

@Composable
fun RowScope.AnimatedLoadingPublishComment() {
    /* FIXME val image = AnimatedImageVector.animatedVectorResource(Res.drawable.ic_timer_animated)
      var atEnd by remember { mutableStateOf(false) }

      val scope = rememberCoroutineScope()
      LaunchedEffect(key1 = true) {

          scope.launch {
              while (true) {
                  delay(300)
                  atEnd = !atEnd
              }
          }
      }
      Image(
          painter = rememberAnimatedVectorPainter(image, atEnd),
          contentDescription = "Timer",
          modifier = Modifier
              .padding(4.dp)
              .align(Alignment.CenterVertically),
          contentScale = ContentScale.Crop
      )*/

}

