package com.mustafin.feed.presentation.views.eventView

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mustafin.feed.utils.events.Tags

/* View со списком тегов в виде FlowRow */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TagsFlowRow(tags: Set<Tags>) {
    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        tags.forEach { tag ->
            TagView(tag = tag)
        }
    }
}

@Composable
@Preview
private fun Preview() {
    TagsFlowRow(tags = setOf(Tags.Meal, Tags.Future, Tags.Lifestyle))
}