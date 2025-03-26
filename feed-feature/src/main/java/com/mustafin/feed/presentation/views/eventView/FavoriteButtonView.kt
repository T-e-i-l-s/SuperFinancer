package com.mustafin.feed.presentation.views.eventView

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.mustafin.feed.R
import com.mustafin.ui_components.presentation.buttons.CustomTinyButton

/* View кнопки избранное */
@Composable
fun FavoriteButtonView(isFavorite: Boolean, onClick: () -> Unit, modifier: Modifier) {
    val mainColor = if (isFavorite) colorResource(id = R.color.yellow) else colorResource(id = R.color.gray)

    CustomTinyButton(
        text = stringResource(if (isFavorite) R.string.in_favorites else R.string.add_to_favorites),
        onClick = onClick,
        icon = painterResource(id = R.drawable.bookmark),
        contentColor = colorResource(id = R.color.white),
        backgroundColor = mainColor.copy(0.9f),
        modifier = modifier
    )
}

@Composable
@Preview
private fun Preview() {
    FavoriteButtonView(true, {}, Modifier)
}