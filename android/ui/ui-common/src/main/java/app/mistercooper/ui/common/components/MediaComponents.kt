package app.mistercooper.ui.common.components

import android.Manifest
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import app.mistercooper.ui.common.R
import app.mistercooper.ui.common.utils.BuildConfigFieldsProvider
import app.mistercooper.ui.common.utils.checkCustomPermission
import app.mistercooper.ui.common.utils.createImageFile
import app.mistercooper.ui.common.utils.requestCameraPermission
import app.mistercooper.ui.common.viewModel.MediaViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject
import java.util.Objects

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectSourceBottomSheet(
    onDismiss: () -> Unit
) {
    val mediaViewModel: MediaViewModel = koinViewModel()
    val buildConfigFieldsProvider: BuildConfigFieldsProvider = koinInject()

    var launchCamera by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    val pickImage = rememberLauncherForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) {
        mediaViewModel.onPhotoSelected(it)
        onDismiss()
    }

    if (launchCamera) {
        val context = LocalContext.current
        val file = context.createImageFile()
        val uri = FileProvider.getUriForFile(
            Objects.requireNonNull(context),
            buildConfigFieldsProvider.get().appId + ".provider",
            file
        )

        CameraLauncherComponent (
            onPhotoProcessFinished = {launchCamera = false
                onDismiss()},
            onPhotoSelected = { uri -> mediaViewModel.onPhotoSelected(uri) },
            destinationUri = uri
        )
    }

    ModalBottomSheet(
        modifier = Modifier
            .fillMaxWidth(),
        onDismissRequest = { onDismiss() },
        dragHandle = { BottomSheetDefaults.DragHandle() },
    ) {
        Box {
            Column(
                modifier = Modifier.fillMaxHeight(0.3f)
            ) {

                Text(
                    modifier = Modifier
                        .padding(10.dp),
                    text = stringResource(R.string.image_selection_title),
                    style = MaterialTheme.typography.headlineMedium
                )
                Text(
                    modifier = Modifier
                        .padding(10.dp),
                    text = stringResource(R.string.image_selection_body),
                    style = MaterialTheme.typography.bodySmall
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Button(
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .padding(10.dp),
                        onClick = {
                            launchCamera = true
                        }) {
                        Icon(
                            modifier = Modifier
                                .height(20.dp)
                                .padding(horizontal = 10.dp),
                            painter = painterResource(R.drawable.ic_camera),
                            contentDescription = stringResource(id = R.string.add_photo_content_description)
                        )
                        Text(text = stringResource(R.string.publish_camera_option))
                    }
                    Button(
                        modifier = Modifier
                            .fillMaxWidth(1f)
                            .padding(10.dp),
                        onClick = {
                            mediaViewModel.getLocalMediaImages()
                            coroutineScope.launch {
                                pickImage.launch(
                                    PickVisualMediaRequest(
                                        ActivityResultContracts.PickVisualMedia.ImageOnly
                                    )
                                )
                            }
                        }) {
                        Icon(
                            modifier = Modifier
                                .height(20.dp)
                                .padding(horizontal = 10.dp),
                            painter = painterResource(R.drawable.ic_add_photo),
                            contentDescription = stringResource(id = R.string.add_photo_content_description)
                        )
                        Text(text = stringResource(R.string.publish_files_option))
                    }
                }
            }
        }
    }
}

@Composable
fun CameraLauncherComponent(
    onPhotoProcessFinished: () -> Unit,
    onPhotoSelected: (uri: Uri) -> Unit,
    destinationUri: Uri
) {
    val context = LocalContext.current
    var capturedImageUri by remember {
        mutableStateOf<Uri>(Uri.EMPTY)
    }

    val cameraLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) { isPhotoTaken ->
            if (isPhotoTaken) {
                capturedImageUri = destinationUri
            } else {
                onPhotoProcessFinished()
            }
        }
    val permissionLauncher = requestCameraPermission(
        onPermissionGranted = { cameraLauncher.launch(destinationUri) },
        onPermissionDenied = {
            Toast.makeText(
                context,
                context.getString(R.string.permission_denied), Toast.LENGTH_SHORT
            ).show()
        })

    if (capturedImageUri.path?.isNotEmpty() == true) {
        onPhotoSelected(capturedImageUri)
        onPhotoProcessFinished()
    }
    LaunchedEffect(Unit) {
        context.checkCustomPermission({ cameraLauncher.launch(destinationUri) }, {
            permissionLauncher.launch(
                Manifest.permission.CAMERA
            )
        })
    }
}