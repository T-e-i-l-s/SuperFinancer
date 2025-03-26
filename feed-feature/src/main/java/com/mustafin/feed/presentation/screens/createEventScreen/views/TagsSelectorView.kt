package com.mustafin.feed.presentation.screens.createEventScreen.views

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mustafin.feed.R
import com.mustafin.feed.utils.events.Tags

/* View списка с возможностью выбора тегов */
@Composable
fun TagsSelectorView(
    selectedTags: Set<Tags>,
    toggleTagSelection: (Tags) -> Unit
) {
    Column {
        Text(
            text = stringResource(id = R.string.tags),
            color = colorResource(id = R.color.gray),
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(Tags.tagsList) { tag ->
                SelectorListItemView(
                    tag = tag,
                    isSelected = selectedTags.contains(tag),
                    toggleTagSelection = { toggleTagSelection(tag) }
                )
            }
        }
    }
}

/* View элемента из списка тегов */
@Composable
private fun SelectorListItemView(
    tag: Tags,
    isSelected: Boolean,
    toggleTagSelection: () -> Unit
) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(
                colorResource(id = tag.colorRes).copy(
                    alpha = if (isSelected) 0.4f else 0.15f
                )
            )
            .clickable(onClick = toggleTagSelection)
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .animateContentSize(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = tag.nameRes),
            style = MaterialTheme.typography.titleSmall,
            color = colorResource(id = R.color.content)
        )

        if (isSelected) {
            Spacer(modifier = Modifier.width(4.dp))

            Icon(
                painter = painterResource(id = R.drawable.x),
                tint = colorResource(id = R.color.content),
                modifier = Modifier.size(12.dp),
                contentDescription = null
            )
        }
    }
}

@Composable
@Preview
private fun Preview() {
    TagsSelectorView(emptySet()) {}

    SelectorListItemView(tag = Tags.Future, true) {}

    SelectorListItemView(tag = Tags.Future, false) {}
}