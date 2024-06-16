
package comment

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Send
import androidx.compose.material.rememberModalBottomSheetState
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import app.mistercooper.social.domain.comment.model.CommentModel
import app.mistercooper.ui_comment_shared.model.PublishCommentUiModel
import comment.viewmodel.CommentViewModel
import common.component.CustomTextField
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.comment
import kotlinproject.composeapp.generated.resources.comment_hint
import kotlinproject.composeapp.generated.resources.comments_empty_body
import kotlinproject.composeapp.generated.resources.comments_empty_title
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CommentsBottomSheetScreen(
    postId: Long,
    writeNow: Boolean,
    onDismiss: () -> Unit
) {
    var modalHeight by remember { mutableStateOf(0) }
    BottomSheetScaffold(
        modifier = Modifier
            .padding(0.dp)
            .onGloballyPositioned {
                modalHeight = it.size.height
            }
            .imePadding()
            .fillMaxSize(),
        sheetContent = { BottomSheetContent(postId, writeNow, modalHeight) }
    ) {

    }
}

@Composable
fun BottomSheetContent(postId: Long,
                       writeNow: Boolean,
                       modalHeight: Int) {
    val viewModel : CommentViewModel = koinViewModel()
    val viewModelState = viewModel.commentUiModel.collectAsState()
    viewModel.getComments(postId)
    Box(modifier = Modifier.fillMaxSize()) {
                CommentsListComponent(comments = viewModelState.value.commentWrapper?.comments) {
                    viewModel.getComments(
                        postId
                    )
                }
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
        CommentsFooterComponent(postId, modalHeight, writeNow, viewModelState.value) { text, id ->
            viewModel.publishComment(
                text,
                id
            )
        }
    }


@Composable
fun CommentsListComponent(comments: List<CommentModel>?, getComments: () -> Unit) {
    if (comments == null) {
        getComments()
    } else {
        LazyColumn() {
            items(comments) { comment ->
                Row(modifier = Modifier.padding(vertical = 10.dp)) {
                    app.mistercooper.ui.common.components.UserMiniatureComponent(comment.user.imageProfileUrl.orEmpty())
                    Column {
                        Row {
                            Text(
                                modifier = Modifier.padding(horizontal = 8.dp),
                                text = comment.user.userName.orEmpty(),
                                style = MaterialTheme.typography.h6
                            )
                            Text(
                                modifier = Modifier.padding(horizontal = 8.dp),
                                text = comment.date.toString(),
                                style = MaterialTheme.typography.body1,
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
fun CommentsFooterComponent(
    postId: Long,
    modalHeight: Int,
    showKeyboard: Boolean,
    uiState: PublishCommentUiModel,
    publishComment: (commentText: String, postId: Long) -> Unit
) {

    var footerHeight by remember { mutableStateOf(0) }
    val focusManager = LocalFocusManager.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .offset {
                IntOffset(
                    0,
                    (modalHeight - footerHeight)
                )
            }
           /* .onGloballyPositioned {
                footerHeight = (((it.size.height * 1)) + bottomPadding + 0).toInt()
            }*/
    ) {
        Box(
            modifier = Modifier
                .background(color = Color.Black)
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 40.dp)

        ) {
            Row(Modifier.fillMaxWidth()) {

                app.mistercooper.ui.common.components.UserMiniatureComponent(uiState.commentWrapper?.myImageUrl.orEmpty())
                var commentText by remember { mutableStateOf("") }
                CustomTextField(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .padding(horizontal = 8.dp)
                        .height(40.dp),
                    onTextChanged = { newText -> commentText = newText },
                    showKeyboard,
                    stringResource(Res.string.comment_hint),
                    singleLine = false
                )
                if (uiState.isLoadingPublish) {
                    AnimatedLoadingPublishComment()
                } else {
                    Icon(
                        Icons.Rounded.Send,
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

