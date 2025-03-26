package com.mustafin.feed.presentation.views.eventView

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import com.mustafin.feed.R
import com.mustafin.feed.presentation.views.LinkedNewsView
import com.mustafin.feed.utils.events.EventModel
import com.mustafin.feed.utils.events.LinkedNewsModel
import com.mustafin.feed.utils.events.Tags

/* View с информацией о событии */
@Composable
fun EventView(
    event: EventModel,
    openNew: (LinkedNewsModel) -> Unit,
    showFavoriteButton: Boolean = true,
    isFavorite: Boolean = false,
    toggleIsFavorite: () -> Unit = {},
) {
    val interactionSource = remember { MutableInteractionSource() }

    var isExpanded by rememberSaveable { mutableStateOf(false) }

    HorizontalDivider(
        modifier = Modifier.fillMaxWidth(),
        thickness = 1.dp,
        color = colorResource(id = R.color.secondary_background)
    )

    Column(Modifier.fillMaxWidth()) {
        Box {
            if (event.images.isNotEmpty()) {
                EventImagesPager(images = event.images)
            }

            if (showFavoriteButton) {
                FavoriteButtonView(
                    isFavorite = isFavorite,
                    onClick = toggleIsFavorite,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(top = 16.dp, start = 16.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = event.text,
            style = MaterialTheme.typography.labelMedium,
            color = colorResource(id = R.color.content),
            maxLines = if (isExpanded) Int.MAX_VALUE else 3,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .animateContentSize()
                .clickable(
                    interactionSource = interactionSource,
                    indication = null,
                ) { isExpanded = !isExpanded }
        )

        Spacer(modifier = Modifier.height(8.dp))

        TagsFlowRow(tags = event.tags)

        event.linkedNews?.let { linkedNewsSafe ->
            Spacer(modifier = Modifier.height(8.dp))

            LinkedNewsView(
                linkedNews = linkedNewsSafe,
                openNew = { openNew(linkedNewsSafe) }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
@Preview
private fun Preview() {
    EventView(
        event = EventModel(
            images = emptyList(),
            text = LoremIpsum(50).values.first(),
            tags = setOf(Tags.Meal, Tags.Future, Tags.Lifestyle),
            linkedNews = null
        ),
        openNew = {},
        showFavoriteButton = true
    )
}