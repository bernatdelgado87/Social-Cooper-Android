package app.mistercooper.ui.registerlogin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import app.mistercooper.ui.common.components.CommonScaffoldTopBar
import app.mistercooper.ui.common.components.CustomTextField
import app.mistercooper.ui.common.components.LoadingComponent
import app.mistercooper.ui.common.components.TextType
import app.mistercooper.ui.common.navigation.GlobalNavigator
import app.mistercooper.ui.common.utils.restartCurrentActivity
import app.mistercooper.ui.register.R
import app.mistercooper.ui_registerlogin_shared.viewmodel.RegisterLoginViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen(globalNavigator: GlobalNavigator) {
    val viewModel : RegisterLoginViewModel = koinViewModel()
    val state = viewModel.registerLoginState.collectAsState()

    var canActivateButton by remember { mutableStateOf(false) }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    canActivateButton = email.isNotEmpty() && password.isNotEmpty()

    if (state.value.registerLoginSuccess) {
        val context = LocalContext.current
        context.restartCurrentActivity()
    }

    CommonScaffoldTopBar(
        globalNavigator = globalNavigator,
        topBarTitle = stringResource(id = R.string.login_now_toolbar_title),
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
                        text = stringResource(R.string.login_now_title),
                        style = MaterialTheme.typography.headlineLarge
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
                        placeholderText = stringResource(id = R.string.register_email),
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
                        placeholderText = stringResource(id = R.string.register_password),
                        type = TextType.PASSWORD
                    )
                    Button(
                        modifier = Modifier
                            .padding(20.dp)
                            .fillMaxWidth(),
                        enabled = canActivateButton,
                        onClick = {
                            viewModel.login(
                                email = email,
                                password = password
                            )
                        }) {
                        Text(text = stringResource(R.string.login_button))
                    }
                    if (state.value.loading) {
                        LoadingComponent()
                    }
                }
            }
        },
        showError = state.value.error
    )
}
