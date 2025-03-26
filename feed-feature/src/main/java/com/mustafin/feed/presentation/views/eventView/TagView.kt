package com.mustafin.feed.presentation.views.eventView

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mustafin.feed.R
import com.mustafin.feed.utils.events.Tags

/* View тега под постом */
@Composable
fun TagView(tag: Tags) {
    Text(
        text = stringResource(id = tag.nameRes),
        style = MaterialTheme.typography.titleSmall,
        color = colorResource(id = R.color.content),
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(colorResource(id = tag.colorRes))
            .padding(vertical = 4.dp, horizontal = 8.dp)
    )
}

@Composable
@Preview
private fun Preview() {
    TagView(tag = Tags.Future)
}