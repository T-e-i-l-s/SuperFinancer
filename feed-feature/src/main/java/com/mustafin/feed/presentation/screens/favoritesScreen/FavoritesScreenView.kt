package com.mustafin.feed.presentation.screens.favoritesScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mustafin.feed.R
import com.mustafin.feed.presentation.views.eventView.EventView
import com.mustafin.feed.utils.events.LinkedNewsModel

/* View экрана со списом избранных событий */
@Composable
fun FavoritesScreenView(
    popBackStack: () -> Unit,
    openNew: (LinkedNewsModel) -> Unit,
    viewModel: FavoritesScreenViewModel = hiltViewModel(),
) {
    val favoriteEvents = viewModel.favoriteEvents.collectAsStateWithLifecycle()

    LazyColumn(
        Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.background)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow_left),
                    contentDescription = null,
                    tint = colorResource(id = R.color.content),
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(24.dp)
                        .clickable(onClick = popBackStack)
                )

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = stringResource(id = R.string.favorites),
                    color = colorResource(id = R.color.content),
                    style = MaterialTheme.typography.displayLarge
                )
            }
        }

        favoriteEvents.value?.let { eventsSafe ->
            if (eventsSafe.isEmpty()) {
                item {
                    Text(
                        text = stringResource(id = R.string.favorites_are_empty),
                        color = colorResource(id = R.color.gray),
                        style = MaterialTheme.typography.labelMedium,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }

            items(eventsSafe, key = { it.id }) { event ->
                EventView(
                    event = event,
                    openNew = openNew,
                    showFavoriteButton = false
                )
            }
        }
    }
}