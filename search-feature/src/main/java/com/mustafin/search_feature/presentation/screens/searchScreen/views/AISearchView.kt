package com.mustafin.search_feature.presentation.screens.searchScreen.views

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.unit.dp
import com.mustafin.search_feature.R
import com.mustafin.search_feature.utils.SearchState
import com.mustafin.search_feature.utils.search.AISearchResultModel
import com.mustafin.ui_components.presentation.loaders.SkeletonLoader

/* View с интерфейсом поиска через ИИ */
@Composable
fun AISearchView(searchState: SearchState, searchResults: AISearchResultModel?) {
    Column(
        Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .border(
                2.dp,
                colorResource(id = com.mustafin.ui_components.R.color.additional),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(16.dp)
            .animateContentSize(),
    ) {
        Row(
            Modifier
                .clip(CircleShape)
                .background(colorResource(id = R.color.additional).copy(0.2f))
                .padding(vertical = 4.dp, horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ai),
                contentDescription = null,
                modifier = Modifier.size(20.dp),
                tint = colorResource(id = R.color.additional)
            )

            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = stringResource(id = R.string.ai_search),
                style = MaterialTheme.typography.labelMedium,
                color = colorResource(id = R.color.additional)
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        when (searchState) {
            in listOf(SearchState.COMPLETED, SearchState.UPDATING) -> {
                searchResults?.let { searchResultsSafe ->
                    Text(
                        text = searchResultsSafe.answer,
                        style = MaterialTheme.typography.labelMedium,
                        color = colorResource(id = com.mustafin.web_view_feature.R.color.content)
                    )
                } ?: Text(
                    text = stringResource(id = R.string.nothing_found),
                    style = MaterialTheme.typography.labelMedium,
                    color = colorResource(id = R.color.gray)
                )
            }

            else -> {
                Column {
                    SkeletonLoader(
                        Modifier
                            .fillMaxWidth()
                            .height(14.dp)
                            .clip(CircleShape)
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    SkeletonLoader(
                        Modifier
                            .width(100.dp)
                            .height(14.dp)
                            .clip(CircleShape)
                    )
                }
            }
        }
    }
}