package registerLogin.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import coil3.compose.AsyncImage

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import common.navigation.NavigationRoute
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.background_social
import kotlinproject.composeapp.generated.resources.register_now_title
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun LoginOrRegisterScreen(globalNavigator: NavController) {

    Box(modifier = Modifier.fillMaxSize()){

        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
            ) {
            Spacer(modifier = Modifier.fillMaxHeight(0.2f))
            Text(
                modifier = Modifier.padding(20.dp),
                text = stringResource(Res.string.register_now_title),
                style = MaterialTheme.typography.h1)

            /* fixme!! only for remote purposes. delete it when use in another scrreeen
                AsyncImage(modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
                model = ImageRequest.Builder(LocalPlatformContext.current)
                    .data(Res.drawable.background_social).build()
                    ,contentDescription = "")*/

            Image(painter = painterResource(Res.drawable.background_social), contentDescription = null)


            Button(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(),
                colors =  ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary),
                onClick = { globalNavigator.navigate(NavigationRoute.REGISTER.name) }) {
                Text(text = "Registrarse")
            }
            OutlinedButton(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth(),
                onClick = { globalNavigator.navigate(NavigationRoute.LOGIN.name) }) {
                Text(text = "Login")
            }
        }
    }
}