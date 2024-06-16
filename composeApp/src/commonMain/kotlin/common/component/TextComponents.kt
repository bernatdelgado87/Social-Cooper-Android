package common.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.ic_visibility
import kotlinproject.composeapp.generated.resources.ic_visibility_off
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

enum class TextType {
    TEXT,
    PASSWORD,
    EMAIL
}

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    onTextChanged: (String) -> Unit,
    showKeyboard: Boolean = false,
    placeholderText: String = "",
    singleLine: Boolean = true,
    type: TextType = TextType.TEXT,
    fontSize: TextUnit = MaterialTheme.typography.body1.fontSize
) {
    var hidePassword by rememberSaveable { mutableStateOf(type == TextType.PASSWORD) }
    var text by rememberSaveable { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }

    if (showKeyboard) {
        LaunchedEffect(Unit) {
            focusRequester.requestFocus()
        }
    }
    BasicTextField(modifier = modifier
        .background(
            MaterialTheme.colors.surface,
            MaterialTheme.shapes.small,
        )
        .focusRequester(focusRequester),
        visualTransformation = if (hidePassword) PasswordVisualTransformation() else VisualTransformation.None,
        value = text,
        onValueChange = {
            text = it
            onTextChanged(it)
        },
        singleLine = singleLine,
        cursorBrush = SolidColor(MaterialTheme.colors.primary),
        textStyle = LocalTextStyle.current.copy(
            color = MaterialTheme.colors.onSurface,
            fontSize = fontSize
        ),
        keyboardOptions = if (type == TextType.EMAIL) KeyboardOptions(keyboardType = KeyboardType.Email) else KeyboardOptions(
            keyboardType = KeyboardType.Text
        ),
        decorationBox = { innerTextField ->
            Row(
                modifier,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(Modifier.weight(1f)) {
                    if (text.isEmpty()) Text(
                        placeholderText,
                        style = LocalTextStyle.current.copy(
                            color = MaterialTheme.colors.onSurface.copy(alpha = 0.3f),
                            fontSize = fontSize
                        )
                    )
                    innerTextField()
                }

                val image = if (hidePassword)
                    Res.drawable.ic_visibility
                else Res.drawable.ic_visibility_off

                val description = if (hidePassword) "Show password" else "Hide password"

                if (type == TextType.PASSWORD) {
                    IconButton(onClick = { hidePassword = !hidePassword }) {
                        Icon(painterResource(image), description)
                    }
                }
            }
        }
    )
}

@Preview()
@Composable
fun CustomTextFieldPreview() {
        CustomTextField(
            modifier = Modifier.height(40.dp),
            { },
            placeholderText = "Escribe algo aquí"
        )
}

@Preview()
@Composable
fun CustomLargeTextFieldPreview() {
        CustomTextField(
            modifier = Modifier.height(40.dp),
            { },
            placeholderText = "Escribe algo aquí",
            singleLine = false
        )
}