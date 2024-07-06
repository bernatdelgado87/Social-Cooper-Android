package registerLogin.view

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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import app.mistercooper.social.navigation.restartNavigation
import app.mistercooper.ui.common.components.LoadingComponent
import common.component.CommonScaffoldTopBar
import common.component.CustomTextField
import common.component.TextType
import common.component.button.PrimaryButton
import kmp_app_template_native.social_cooper.composeapp.generated.resources.Res
import kmp_app_template_native.social_cooper.composeapp.generated.resources.login_button
import kmp_app_template_native.social_cooper.composeapp.generated.resources.login_now_title
import kmp_app_template_native.social_cooper.composeapp.generated.resources.login_now_toolbar_title
import kmp_app_template_native.social_cooper.composeapp.generated.resources.register_email
import kmp_app_template_native.social_cooper.composeapp.generated.resources.register_password
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import registerLogin.viewmodel.RegisterLoginViewModel

@Composable
fun LoginScreen(globalNavigator: NavController) {
    val viewModel = koinViewModel<RegisterLoginViewModel>()
    val state = viewModel.registerLoginState.collectAsState()

    var canActivateButton by remember { mutableStateOf(false) }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    canActivateButton = email.isNotEmpty() && password.isNotEmpty()

    if (state.value.registerLoginSuccess) {
        globalNavigator.restartNavigation()
    }

    CommonScaffoldTopBar(
        globalNavigator = globalNavigator,
        topBarTitle = stringResource(Res.string.login_now_toolbar_title),
        content = { modifier ->


            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .imePadding()
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        modifier = Modifier.padding(vertical = 40.dp),
                        text = stringResource(Res.string.login_now_title),
                        style = MaterialTheme.typography.h1
                    )

                    CustomTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentSize(align = Alignment.BottomStart, unbounded = false)
                            .padding(horizontal = 8.dp, vertical = 10.dp),
                        onTextChanged = { newText ->
                            email = newText
                        },
                        showKeyboard = false,
                        placeholderText = stringResource(Res.string.register_email),
                        type = TextType.EMAIL
                    )

                    CustomTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentSize(align = Alignment.BottomStart, unbounded = false)
                            .padding(horizontal = 8.dp, vertical = 10.dp),
                        onTextChanged = { newText ->
                            password = newText
                        },
                        showKeyboard = false,
                        placeholderText = stringResource(Res.string.register_password),
                        type = TextType.PASSWORD
                    )
                    PrimaryButton(
                        enabled = canActivateButton,
                        onClick = {
                            viewModel.login(
                                email = email,
                                password = password
                            )
                        },
                        text = stringResource(Res.string.login_button)
                    )
                    if (state.value.loading) {
                        LoadingComponent()
                    }
                }
            }
        },
        showError = state.value.error
    )
}
