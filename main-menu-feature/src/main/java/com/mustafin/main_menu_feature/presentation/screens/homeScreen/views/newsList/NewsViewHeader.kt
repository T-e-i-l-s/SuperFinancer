package com.mustafin.main_menu_feature.presentation.screens.homeScreen.views.newsList

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.mustafin.core.utils.extensions.toRuZoneDateFormat
import com.mustafin.core.utils.news.NewsModel
import com.mustafin.main_menu_feature.R

/* View верхней части блока с новостью */
@Composable
fun NewsViewHeader(news: NewsModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = R.drawable.at_sign),
                contentDescription = null,
                tint = colorResource(id = R.color.gray),
                modifier = Modifier.size(20.dp)
            )

            Spacer(modifier = Modifier.width(2.dp))

            Text(
                text = news.source,
                style = MaterialTheme.typography.titleMedium,
                color = colorResource(id = R.color.gray),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }

        Text(
            text = news.publishedAt.toRuZoneDateFormat(),
            style = MaterialTheme.typography.titleMedium,
            color = colorResource(id = R.color.gray),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}