package com.mustafin.feed.presentation.views.eventView

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.mustafin.feed.R

/* View со всеми изображениями события */
@Composable
fun EventImagesPager(images: List<String>) {
    val imagesPagerState = rememberPagerState(pageCount = { images.size })

    Box {
        HorizontalPager(state = imagesPagerState) { index ->
            AsyncImage(
                model = images[index],
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(400.dp)
                    .background(colorResource(id = R.color.image_background)),
                contentScale = ContentScale.FillHeight
            )
        }

        if (images.size > 1) {
            Row(
                Modifier
                    .padding(8.dp)
                    .align(Alignment.BottomCenter)
                    .clip(CircleShape)
                    .background(colorResource(id = R.color.background).copy(0.5f))
                    .padding(2.dp),
                horizontalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                for (i in images.indices) {
                    Spacer(
                        modifier = Modifier
                            .size(6.dp)
                            .clip(CircleShape)
                            .background(
                                if (imagesPagerState.currentPage == i) colorResource(id = R.color.content)
                                else colorResource(id = R.color.gray)
                            )
                    )
                }
            }
        }
    }
}