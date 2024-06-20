package publish

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import app.mistercooper.ui.common.components.LoadingComponent
import common.component.CommonScaffoldTopBar
import common.component.CustomTextField
import common.component.SelectMediaComponent
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.post_enter_text_hint
import kotlinproject.composeapp.generated.resources.publish_action_button
import kotlinproject.composeapp.generated.resources.publish_now_title
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import publish.viewmodel.PublishViewModel

@Composable
fun PublishHomeScreen(
    globalNavigator: NavController,
) {
    var canActivateButton by remember { mutableStateOf(false) }
    var text by remember { mutableStateOf("") }

    val publishViewModel: PublishViewModel = koinViewModel()
    val publishState = publishViewModel.publishUiModelState.collectAsState()
    //media
    var imageBitmap by remember { mutableStateOf<ImageBitmap?>(null) }
    var imageByteArray by remember { mutableStateOf<ByteArray?>(null) }


    if (publishState.value.postPublishedSuccess) {
        /* fixme val context = LocalContext.current
        context.restartCurrentActivity()*/
    }
    CommonScaffoldTopBar(
        globalNavigator = globalNavigator,
        topBarTitle = stringResource(Res.string.publish_now_title),
        content = { modifier ->
            PublishPostContentView(
                modifier = modifier,
                onImageSelected = { pair ->
                    imageByteArray = pair?.first
                    imageBitmap = pair?.second
                    pair?.let {
                        canActivateButton = true
                    }
                },
                imageBitmap = imageBitmap,
                onTextChanged = { newText -> text = newText }
            )
            if (publishState.value.loading) {
                LoadingComponent(modifier)
            }
        },
        showError = publishState.value.error,
        actions = {
            Row {
                Button(modifier = Modifier,
                    enabled = canActivateButton,
                    onClick = {
                        imageByteArray?.let { file ->
                            publishViewModel.publishPost(text, file, "fixme") //fixme
                        }
                    }) {
                    Text(text = stringResource(Res.string.publish_action_button))
                }
            }
        })
}


@Composable
fun PublishPostContentView(
    modifier: Modifier,
    onImageSelected: (Pair<ByteArray, ImageBitmap>?) -> Unit,
    imageBitmap: ImageBitmap?,
    onTextChanged: (text: String) -> Unit
) {
    var text by remember { mutableStateOf("") }

    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
        ) {
            SelectMediaComponent(imageBitmap, onImageSelected = { pair ->
                onImageSelected(pair)
            })
            CustomTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(align = Alignment.BottomStart, unbounded = false)
                    .padding(horizontal = 8.dp),
                onTextChanged = { newText ->
                    text = newText
                    onTextChanged(newText)
                },
                showKeyboard = false,
                stringResource(Res.string.post_enter_text_hint),
                singleLine = false
            )
        }
    }
}