package com.mustafin.feed.presentation.screens.createEventScreen.views

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mustafin.feed.R

/* View поля ввода для текста в посте */
@Composable
fun EventTextEditView(
    value: String,
    onValueChange: (String) -> Unit
) {
    val textFieldScrollState = rememberScrollState()

    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = stringResource(id = R.string.publication_text),
                color = colorResource(id = R.color.gray),
                style = MaterialTheme.typography.labelMedium
            )
        },
        colors = TextFieldDefaults.colors(
            unfocusedTextColor = colorResource(id = R.color.content),
            focusedTextColor = colorResource(id = R.color.content),
            errorTextColor = colorResource(id = R.color.content),
            cursorColor = colorResource(id = R.color.content),
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            errorContainerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
            unfocusedPlaceholderColor = colorResource(id = R.color.gray),
            focusedPlaceholderColor = colorResource(id = R.color.gray),
            errorPlaceholderColor = colorResource(id = R.color.gray)
        ),
        textStyle = MaterialTheme.typography.labelMedium,
        shape = RectangleShape,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(textFieldScrollState)
            .padding(bottom = 300.dp)
    )
}

@Composable
@Preview
private fun Preview() {
    EventTextEditView(
        ""
    ) {}
}