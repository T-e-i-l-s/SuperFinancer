package com.mustafin.main_menu_feature.presentation.screens.homeScreen.views.skeletons

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mustafin.ui_components.presentation.loaders.SkeletonLoader

/* View скелетона списка тикеров */
@Composable
fun TickersListSkeleton() {
    Column {
        SkeletonLoader(
            Modifier
                .padding(horizontal = 16.dp)
                .clip(CircleShape)
                .size(60.dp, 16.dp)
        )

        Spacer(modifier = Modifier.height(4.dp))

        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            userScrollEnabled = false
        ) {
            items(5) {
                SkeletonLoader(
                    modifier = Modifier
                        .size(height = 96.dp, width = 150.dp)
                        .clip(RoundedCornerShape(16.dp))
                )
            }
        }
    }
}

@Composable
@Preview
private fun Preview() {
    TickersListSkeleton()
}