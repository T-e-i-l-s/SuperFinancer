package com.mustafin.feed.presentation.screens.feedScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mustafin.feed.R
import com.mustafin.feed.presentation.views.eventView.EventView
import com.mustafin.feed.utils.events.LinkedNewsModel
import com.mustafin.ui_components.presentation.buttons.CustomTinyButton

/* View экрана ленты в приложении */
@Composable
fun FeedScreenView(
    navigateToFavoritesScreen: () -> Unit,
    navigateToCreateEventScreen: () -> Unit,
    openNew: (LinkedNewsModel) -> Unit,
    viewModel: FeedScreenViewModel = hiltViewModel(),
) {
    val events = viewModel.events.collectAsStateWithLifecycle()
    val favoritesIds = viewModel.favoritesIds.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.background))
    ) {
        LazyColumn(
            Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.background)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Spacer(modifier = Modifier.height(16.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = stringResource(id = R.string.events),
                        style = MaterialTheme.typography.displayLarge,
                        color = colorResource(id = R.color.content),
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 16.dp)
                    )

                    CustomTinyButton(
                        text = stringResource(R.string.favorites),
                        onClick = navigateToFavoritesScreen,
                        icon = painterResource(id = R.drawable.bookmark),
                        contentColor = colorResource(id = R.color.content),
                        backgroundColor = colorResource(id = R.color.secondary_background),
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
            }

            events.value?.let { eventsSafe ->
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
                        isFavorite = favoritesIds.value.contains(event.id),
                        openNew = openNew,
                        toggleIsFavorite = { viewModel.toggleIsFavorite(event.id) }
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(80.dp))
            }
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