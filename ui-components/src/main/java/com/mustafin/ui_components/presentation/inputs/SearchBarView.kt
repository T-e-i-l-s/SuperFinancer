package com.mustafin.ui_components.presentation.inputs

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mustafin.ui_components.R

/* View поисковой строки */
@Composable
fun SearchBarView(
    modifier: Modifier = Modifier,
    value: String = "",
    onValueChange: (String) -> Unit = {},
    search: () -> Unit = {},
    isEnabled: Boolean = true,
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        leadingIcon = {
            IconButton(onClick = search, enabled = isEnabled) {
                Icon(
                    painter = painterResource(id = R.drawable.search),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp),
                    tint = colorResource(id = R.color.content)
                )
            }
        },
        placeholder = {
            Text(
                text = stringResource(id = R.string.search),
                color = colorResource(id = R.color.gray),
                style = MaterialTheme.typography.labelMedium
            )
        },
        colors = TextFieldDefaults.colors(
            unfocusedTextColor = colorResource(id = R.color.gray),
            focusedTextColor = colorResource(id = R.color.content),
            errorTextColor = colorResource(id = R.color.content),
            disabledTextColor = colorResource(id = R.color.content),
            cursorColor = colorResource(id = R.color.content),
            focusedContainerColor = colorResource(id = R.color.secondary_background),
            unfocusedContainerColor = colorResource(id = R.color.secondary_background),
            disabledContainerColor = colorResource(id = R.color.secondary_background),
            errorContainerColor = colorResource(id = R.color.secondary_background),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            unfocusedPlaceholderColor = colorResource(id = R.color.gray),
            focusedPlaceholderColor = colorResource(id = R.color.gray),
            errorPlaceholderColor = colorResource(id = R.color.gray),
            disabledPlaceholderColor = colorResource(id = R.color.gray),
        ),
        textStyle = MaterialTheme.typography.labelMedium,
        shape = CircleShape,
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Search,
            keyboardType = KeyboardType.Text,
        ),
        keyboardActions = KeyboardActions(
            onSearch = { search() }
        ),
        enabled = isEnabled,
        modifier = modifier
    )
}

@Composable
@Preview
private fun Preview() {
    SearchBarView()
}