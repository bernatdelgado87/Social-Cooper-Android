package common.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import common.navigation.GlobalNavigator
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.back
import kotlinproject.composeapp.generated.resources.error_message
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource

@Composable
fun CommonScaffoldTopBar(globalNavigator: GlobalNavigator, topBarTitle: String, showError: Boolean, content: @Composable (modifier: Modifier) -> Unit, actions: @Composable RowScope.() -> Unit = {}){
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        topBar = {
            TopAppBar(
                title = { Text(text = topBarTitle) },
                navigationIcon = if (globalNavigator.nativeController.previousBackStackEntry != null) {
                    {
                        IconButton(onClick = { globalNavigator.nativeController.navigateUp() }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                                contentDescription = stringResource(Res.string.back)
                            )
                        }
                    }
                } else {
                    {
                        //nothing to do
                    }
                },
                actions = actions

            )
        },
        content = { padding ->
            content(Modifier.padding(padding))
        }
    )
    if (showError) {
        val errorMessage = stringResource(Res.string.error_message)
        LaunchedEffect(Unit) {
            scope.launch {
                snackbarHostState.showSnackbar(errorMessage)
            }
        }
    }
}

@Composable
fun CommonScaffoldBottomBar(content: @Composable (modifier: Modifier) -> Unit, showError: Boolean, actionFloatingButton: () -> Unit, iconVectorFloatingButton: ImageVector) {
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        floatingActionButton = {
            Box() {
                FloatingActionButton(
                    onClick = { actionFloatingButton() },
                    shape = CircleShape,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(80.dp)
                ) {
                    Icon(
                        imageVector = iconVectorFloatingButton,
                        contentDescription = "add",
                        modifier = Modifier.size(45.dp)
                    )
                }
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        content = { padding ->
            content(Modifier.padding(padding))
        }
    )
    if (showError) {
        val errorMessage = stringResource(Res.string.error_message)
        LaunchedEffect(Unit) {
            scope.launch {
                snackbarHostState.showSnackbar(errorMessage)
            }
        }
    }
}