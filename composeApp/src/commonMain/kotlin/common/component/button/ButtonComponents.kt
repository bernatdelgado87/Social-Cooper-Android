package common.component.button

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import common.navigation.NavigationRoute

@Composable
fun PrimaryButton(modifier: Modifier = Modifier, onClick: () -> Unit){
    Button(
        modifier = modifier
            .padding(20.dp)
            .fillMaxWidth(),
        colors =  ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary),
        onClick = { onClick() }) {
        Text(text = "Registrarse")
    }
}