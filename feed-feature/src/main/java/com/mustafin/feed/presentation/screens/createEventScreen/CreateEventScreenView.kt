package com.mustafin.feed.presentation.screens.createEventScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import com.mustafin.feed.presentation.screens.createEventScreen.views.AddedImagesListView
import com.mustafin.feed.presentation.screens.createEventScreen.views.CreateEventScreenBottomRow
import com.mustafin.feed.presentation.screens.createEventScreen.views.EventTextEditView
import com.mustafin.feed.presentation.screens.createEventScreen.views.TagsSelectorView
import com.mustafin.feed.presentation.views.LinkedNewsView
import com.mustafin.feed.utils.events.LinkedNewsModel

/* View экрана создания поста */
@Composable
fun CreateEventScreenView(
    linkedNews: LinkedNewsModel?,
    popBackStack: () -> Unit,
    navigateToFeedScreen: () -> Unit,
    viewModel: CreateEventScreenViewModel = hiltViewModel()
) {
    val createEventEnabled = viewModel.createEventEnabled.collectAsStateWithLifecycle()
    val selectedTags = viewModel.selectedTags.collectAsStateWithLifecycle()
    val text = viewModel.text.collectAsStateWithLifecycle()
    val imagesPaths = viewModel.imagesPaths.collectAsStateWithLifecycle()


    Column(
        Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.background))
    ) {
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
                text = stringResource(id = R.string.create_event),
                color = colorResource(id = R.color.content),
                style = MaterialTheme.typography.displayLarge
            )
        }

        TagsSelectorView(
            selectedTags = selectedTags.value,
            toggleTagSelection = viewModel::toggleTagSelection
        )

        Spacer(modifier = Modifier.height(8.dp))

        Box(Modifier.weight(1f)) {
            EventTextEditView(
                value = text.value,
                onValueChange = viewModel::setText
            )

            Column(Modifier.align(Alignment.BottomStart)) {
                AddedImagesListView(
                    imagesPaths.value,
                    viewModel::removeImageAtIndex
                )

                if (imagesPaths.value.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(8.dp))
                }

                linkedNews?.let { linkedNewsSafe ->
                    LinkedNewsView(linkedNewsSafe)

                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }

        CreateEventScreenBottomRow(
            isCreateEnabled = createEventEnabled.value,
            createEvent = {
                viewModel.createEvent(
                    linkedNews = linkedNews,
                    whenCompleted = navigateToFeedScreen
                )
            },
            addImage = viewModel::addImage
        )
    }
}