package common.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import common.media.PermissionCallback
import common.media.PermissionStatus
import common.media.PermissionType
import common.media.createPermissionsManager
import common.media.rememberCameraManager
import common.media.rememberGalleryManager
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.img_placeholder_profile
import kotlinproject.composeapp.generated.resources.select_image
import kotlinproject.composeapp.generated.resources.your_photo_content_description
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun SelectMediaComponent(
    imageBitmap: ImageBitmap?,
    onImageSelected:(Pair<ByteArray, ImageBitmap>?)-> Unit,
    ) {
    //Camera and gallery
    val coroutineScope = rememberCoroutineScope()

    var imageSourceOptionDialog by remember { mutableStateOf(value = false) }
    var launchCamera by remember { mutableStateOf(value = false) }
    var launchGallery by remember { mutableStateOf(value = false) }
    var launchSetting by remember { mutableStateOf(value = false) }
    var permissionRationalDialog by remember { mutableStateOf(value = false) }
    val permissionsManager = createPermissionsManager(object : PermissionCallback {
        override fun onPermissionStatus(
            permissionType: PermissionType,
            status: PermissionStatus
        ) {
            when (status) {
                PermissionStatus.GRANTED -> {
                    when (permissionType) {
                        PermissionType.CAMERA -> launchCamera = true
                        PermissionType.GALLERY -> launchGallery = true
                    }
                }

                else -> {
                    permissionRationalDialog = true
                }
            }
        }


    })

    val cameraManager = rememberCameraManager {
        coroutineScope.launch {
            val pair = withContext(Dispatchers.Default) {
                it?.toPairArrayBiteBitmap()
            }
            onImageSelected(pair)
        }
    }

    val galleryManager = rememberGalleryManager {
        coroutineScope.launch {
            val pair = withContext(Dispatchers.Default) {
                it?.toPairArrayBiteBitmap()
            }
            onImageSelected(pair)
        }
    }

    if (imageSourceOptionDialog) {
        ImageSourceOptionDialog(onDismissRequest = {
            imageSourceOptionDialog = false
        }, onGalleryRequest = {
            imageSourceOptionDialog = false
            launchGallery = true
        }, onCameraRequest = {
            imageSourceOptionDialog = false
            launchCamera = true
        })
    }
    if (launchGallery) {
        if (permissionsManager.isPermissionGranted(PermissionType.GALLERY)) {
            galleryManager.launch()
        } else {
            permissionsManager.askPermission(PermissionType.GALLERY)
        }
        launchGallery = false
    }
    if (launchCamera) {
        if (permissionsManager.isPermissionGranted(PermissionType.CAMERA)) {
            cameraManager.launch()
        } else {
            permissionsManager.askPermission(PermissionType.CAMERA)
        }
        launchCamera = false
    }
    if (launchSetting) {
        permissionsManager.launchSettings()
        launchSetting = false
    }
    if (permissionRationalDialog) {
        AlertMessageDialog(title = "Permission Required",
            message = "To set your profile picture, please grant this permission. You can manage permissions in your device settings.",
            positiveButtonText = "Settings",
            negativeButtonText = "Cancel",
            onPositiveClick = {
                permissionRationalDialog = false
                launchSetting = true

            },
            onNegativeClick = {
                permissionRationalDialog = false
            })

    }


    Box(
        modifier = Modifier
            .fillMaxHeight(0.2f)
            .padding(20.dp)
            .background(color = Color.Transparent),
    ) {
        if (imageBitmap != null) {
            Image(
                bitmap = imageBitmap,
                contentDescription = stringResource(Res.string.select_image),
                modifier = Modifier.size(100.dp).clip(CircleShape).clickable {
                    imageSourceOptionDialog = true
                },
                contentScale = ContentScale.Crop
            )
        } else {
            Image(
                modifier = Modifier.size(100.dp).clip(CircleShape).clickable {
                    imageSourceOptionDialog = true
                },
                painter = painterResource((Res.drawable.img_placeholder_profile)),
                contentDescription = stringResource(Res.string.your_photo_content_description,)
            )
        }
    }
}