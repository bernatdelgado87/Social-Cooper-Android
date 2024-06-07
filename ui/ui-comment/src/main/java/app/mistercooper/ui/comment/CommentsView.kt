@file:OptIn(ExperimentalMaterial3Api::class)

package app.mistercooper.ui.comment

import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.animation.graphics.res.animatedVectorResource
import androidx.compose.animation.graphics.res.rememberAnimatedVectorPainter
import androidx.compose.animation.graphics.vector.AnimatedImageVector
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Send
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import app.mistercooper.domain.comment.model.CommentModel
import app.mistercooper.ui.common.utils.toPx
import app.mistercooper.ui_comment_shared.model.PublishCommentUiModel
import app.mistercooper.ui_comment_shared.viewmodel.CommentViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommentsBottomSheetScreen(
    postId: Long,
    writeNow: Boolean,
    onDismiss: () -> Unit
) {
    val viewModel : CommentViewModel = koinViewModel()
    val viewModelState = viewModel.commentUiModel.collectAsState()

    val modalBottomSheetState = rememberModalBottomSheetState()
    var modalHeight by remember { mutableStateOf(0) }

    viewModel.getComments(postId)

    ModalBottomSheet(
        modifier = Modifier
            .padding(0.dp)
            .onGloballyPositioned {
                modalHeight = it.size.height
            }
            .imePadding()
            .fillMaxSize(),
        onDismissRequest = { onDismiss() },
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            viewModelState.value.commentWrapper?.let {
                if (viewModelState.value.commentWrapper!!.comments.isNotEmpty()) {
                    CommentsListComponent(comments = viewModelState.value.commentWrapper?.comments) {
                        viewModel.getComments(
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
                            text = stringResource(R.string.comments_empty_title),
                            style = MaterialTheme.typography.headlineLarge,
                        )

                        Text(
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(vertical = 20.dp),
                            text = stringResource(R.string.comments_empty_body),
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.headlineMedium,
                        )
                    }
                }
            }
            CommentsFooterComponent(postId, modalHeight, modalBottomSheetState, writeNow, viewModelState.value) { text, id ->
                viewModel.publishComment(
                    text,
                    id
                )
            }
        }
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
                                style = MaterialTheme.typography.headlineSmall
                            )
                            Text(
                                modifier = Modifier.padding(horizontal = 8.dp),
                                text = comment.date.toString(),
                                style = MaterialTheme.typography.bodySmall,
                                maxLines = 1
                            )
                        }
                        Row(
                            modifier = Modifier.padding(horizontal = 8.dp),
                        ) {
                            Text(
                                text = comment.text,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommentsFooterComponent(
    postId: Long,
    modalHeight: Int,
    modalBottomSheetState: SheetState,
    showKeyboard: Boolean,
    uiState: PublishCommentUiModel,
    publishComment: (commentText: String, postId: Long) -> Unit
) {

    var footerHeight by remember { mutableStateOf(0) }
    val bottomPadding = ButtonDefaults.MinHeight.toPx()
    val focusManager = LocalFocusManager.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .offset {
                IntOffset(
                    0,
                    (modalHeight - modalBottomSheetState.requireOffset() - footerHeight).toInt()
                )
            }
            .onGloballyPositioned {
                footerHeight = (((it.size.height * 1)) + bottomPadding + 0).toInt()
            }
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
                app.mistercooper.ui.common.components.CustomTextField(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .padding(horizontal = 8.dp)
                        .height(40.dp),
                    onTextChanged = { newText -> commentText = newText },
                    showKeyboard,
                    stringResource(id = R.string.comment_hint),
                    singleLine = false
                )
                if (uiState.isLoadingPublish) {
                    AnimatedLoadingPublishComment()
                } else {
                    Icon(
                        Icons.Rounded.Send,
                        contentDescription = stringResource(id = R.string.comment),
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

@OptIn(ExperimentalAnimationGraphicsApi::class)
@Composable
fun RowScope.AnimatedLoadingPublishComment() {
    val image = AnimatedImageVector.animatedVectorResource(R.drawable.ic_timer_animated)
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
    )

}

