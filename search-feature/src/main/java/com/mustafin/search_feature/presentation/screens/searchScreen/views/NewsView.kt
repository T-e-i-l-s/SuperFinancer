package com.mustafin.search_feature.presentation.screens.searchScreen.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.mustafin.search_feature.R
import com.mustafin.search_feature.utils.search.SearchNewsModel

/* View с информацией о новости, которая отображается при поиске */
@Composable
fun NewsView(news: SearchNewsModel, onClick: () -> Unit) {
    HorizontalDivider(
        modifier = Modifier.fillMaxWidth(),
        thickness = 1.dp,
        color = colorResource(id = R.color.secondary_background)
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = news.description,
            style = MaterialTheme.typography.labelMedium,
            color = colorResource(id = R.color.content),
            modifier = Modifier.weight(1f)
        )

        Icon(
            painter = painterResource(id = R.drawable.chevron_right),
            contentDescription = null,
            modifier = Modifier.size(20.dp),
            tint = colorResource(id = R.color.gray)
        )
    }
}