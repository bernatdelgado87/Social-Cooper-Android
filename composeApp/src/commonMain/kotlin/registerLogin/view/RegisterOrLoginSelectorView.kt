package registerLogin.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import common.component.button.PrimaryButton
import common.component.button.TransparentButton
import common.navigation.NavigationRoute
import kmp_app_template_native.social_cooper.composeapp.generated.resources.Res
import kmp_app_template_native.social_cooper.composeapp.generated.resources.background_social
import kmp_app_template_native.social_cooper.composeapp.generated.resources.login_button
import kmp_app_template_native.social_cooper.composeapp.generated.resources.register_button
import kmp_app_template_native.social_cooper.composeapp.generated.resources.register_now_title
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun LoginOrRegisterScreen(globalNavigator: NavController) {

    Box(modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.fillMaxHeight(0.2f))
            Text(
                modifier = Modifier.padding(20.dp),
                text = stringResource(Res.string.register_now_title),
                style = MaterialTheme.typography.h1
            )

            Image(
                painter = painterResource(Res.drawable.background_social),
                contentDescription = null
            )

            PrimaryButton(
                text = stringResource(Res.string.register_button),
                onClick = { globalNavigator.navigate(NavigationRoute.REGISTER.name) })
            TransparentButton(
                text = stringResource(Res.string.login_button),
                onClick = { globalNavigator.navigate(NavigationRoute.LOGIN.name) })
        }
    }
}