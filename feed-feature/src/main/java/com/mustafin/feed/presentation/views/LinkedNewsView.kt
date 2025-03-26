package com.mustafin.feed.presentation.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.mustafin.feed.R
import com.mustafin.feed.utils.events.LinkedNewsModel

/* View с краткой информацией о привязанной к событию новости */
@Composable
fun LinkedNewsView(linkedNews: LinkedNewsModel, openNew: () -> Unit = {}) {
    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(colorResource(id = R.color.secondary_background))
            .clickable(onClick = openNew)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        linkedNews.imageUrl?.let { imageUrlSafe ->
            AsyncImage(
                model = imageUrlSafe,
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.FillHeight
            )

            Spacer(modifier = Modifier.width(8.dp))
        }

        Text(
            text = linkedNews.title,
            color = colorResource(id = R.color.content),
            style = MaterialTheme.typography.titleMedium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
@Preview
private fun Preview() {
    LinkedNewsView(
        LinkedNewsModel(
            LoremIpsum(2).values.first(),
            "",
            ""
        )
    )
}