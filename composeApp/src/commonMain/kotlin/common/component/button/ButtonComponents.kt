package common.component.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PrimaryButton(modifier: Modifier = Modifier, text: String, onClick: () -> Unit, enabled: Boolean = true) {
    Button(
        modifier = modifier
            .padding(20.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(30),
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary),
        onClick = { onClick() }) {
        TextButton(text)
    }
}

@Composable
fun TransparentButton(modifier: Modifier = Modifier, text: String, onClick: () -> Unit, enabled: Boolean = true) {
    OutlinedButton(
        modifier = modifier
            .padding(20.dp)
            .fillMaxWidth(),
        enabled = enabled,
        border = BorderStroke(1.dp, MaterialTheme.colors.primary),
        shape = RoundedCornerShape(30),
        colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colors.primary),
        onClick = { onClick() }) {

        TextButton(text)
    }
}

@Composable
private fun TextButton(text: String) {
    Text(modifier = Modifier.padding(8.dp),
        text = text,
        style = MaterialTheme.typography.body1)
}