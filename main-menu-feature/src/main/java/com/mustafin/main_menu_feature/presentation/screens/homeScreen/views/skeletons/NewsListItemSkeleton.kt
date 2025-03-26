package com.mustafin.main_menu_feature.presentation.screens.homeScreen.views.skeletons

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mustafin.main_menu_feature.R
import com.mustafin.ui_components.presentation.loaders.SkeletonLoader

/* View скелетона элемента списка новостей */
@Composable
fun NewsListItemSkeleton() {
    HorizontalDivider(
        modifier = Modifier.fillMaxWidth(),
        thickness = 1.dp,
        color = colorResource(id = R.color.secondary_background)
    )

    Column {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            SkeletonLoader(
                Modifier
                    .clip(CircleShape)
                    .size(100.dp, 16.dp)
            )
            SkeletonLoader(
                Modifier
                    .clip(CircleShape)
                    .size(100.dp, 16.dp)
            )
        }

        SkeletonLoader(
            Modifier
                .fillMaxWidth()
                .height(250.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        SkeletonLoader(
            Modifier
                .padding(horizontal = 16.dp)
                .clip(CircleShape)
                .size(100.dp, 16.dp)
        )

        Spacer(modifier = Modifier.height(4.dp))

        SkeletonLoader(
            Modifier
                .padding(horizontal = 16.dp)
                .clip(CircleShape)
                .fillMaxWidth()
                .height(12.dp)
        )

        Spacer(modifier = Modifier.height(4.dp))

        SkeletonLoader(
            Modifier
                .padding(horizontal = 16.dp)
                .clip(CircleShape)
                .width(250.dp)
                .height(12.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
@Preview
private fun Preview() {
    NewsListItemSkeleton()
}