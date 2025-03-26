package com.mustafin.main_menu_feature.presentation.screens.fullNewsScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mustafin.main_menu_feature.R
import com.mustafin.web_view_feature.presentation.WebViewImpl

/* View который реализует интерфейс экрана с полной информаицей о новости */
@Composable
fun FullNewsScreen(
    url: String,
    popBackNavigationStack: () -> Unit,
    navigateToCreateEventScreen: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.background))
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Icon(
                painter = painterResource(id = R.drawable.arrow_left),
                contentDescription = null,
                tint = colorResource(id = R.color.content),
                modifier = Modifier
                    .padding(16.dp)
                    .clip(CircleShape)
                    .size(24.dp)
                    .clickable(onClick = popBackNavigationStack)
            )

            WebViewImpl(url = url)
        }

        FloatingActionButton(
            onClick = navigateToCreateEventScreen,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            containerColor = colorResource(id = R.color.content)
        ) {
            Text(
                text = stringResource(id = R.string.create_event_button_text),
                style = MaterialTheme.typography.displayLarge,
                color = colorResource(id = R.color.background)
            )
        }
    }
}