package registerLogin.view

import StandarDialog
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.MaterialTheme
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
import common.component.button.PrimaryButton
import common.navigation.NavigationRoute
import common.navigation.navigate
import kmp_app_template_native.social_cooper.composeapp.generated.resources.Res
import kmp_app_template_native.social_cooper.composeapp.generated.resources.register_button
import kmp_app_template_native.social_cooper.composeapp.generated.resources.register_name
import kmp_app_template_native.social_cooper.composeapp.generated.resources.register_toolbar_title
import kmp_app_template_native.social_cooper.composeapp.generated.resources.registration_registrate_now_title
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import registerLogin.viewmodel.RegisterLoginViewModel

@Composable
fun RegisterScreen(
    globalNavigator: NavController,
) {
    val viewModel: RegisterLoginViewModel = koinViewModel()
    val state = viewModel.registerLoginState.collectAsState()
    var userName by remember { mutableStateOf("") }
    var canActivateButton by remember { mutableStateOf(false) }
    //media
    var imageBitmap by remember { mutableStateOf<ImageBitmap?>(null) }
    var imageByteArray by remember { mutableStateOf<ByteArray?>(null) }
    var openAlertDialog = remember { mutableStateOf(false) }

    canActivateButton = userName.isNotEmpty() && imageByteArray != null

    if (state.value.registerLoginSuccess) {
        globalNavigator.navigate(NavigationRoute.PUBLISH_NOW)
    }

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

    CommonScaffoldTopBar(
        globalNavigator = globalNavigator,
        topBarTitle = stringResource(Res.string.register_toolbar_title),
        content = { modifier ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .imePadding()
            ) {

                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        modifier = Modifier.padding(20.dp),
                        text = stringResource(Res.string.registration_registrate_now_title),
                        style = MaterialTheme.typography.h1
                    )
                    SelectMediaComponent(imageBitmap, onImageSelected = { pair ->
                        imageBitmap = pair?.second
                        imageByteArray = pair?.first
                    })
                    CustomTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentSize(align = Alignment.BottomStart, unbounded = false)
                            .padding(horizontal = 8.dp, vertical = 10.dp),
                        onTextChanged = { newText ->
                            userName = newText
                        },
                        showKeyboard = false,
                        placeholderText = stringResource(Res.string.register_name),
                    )
                    PrimaryButton(
                        modifier = Modifier
                            .padding(20.dp)
                            .fillMaxWidth(),
                        enabled = canActivateButton,
                        text = stringResource(Res.string.register_button),
                        onClick = {
                            imageByteArray?.let {
                                viewModel.registerExistingUser(
                                    userName = userName,
                                    file = imageByteArray!!
                                )
                            }
                        })
                    if (state.value.loading) {
                        LoadingComponent()
                    }
                }
            }
        },
        showError = state.value.error
    )
}


