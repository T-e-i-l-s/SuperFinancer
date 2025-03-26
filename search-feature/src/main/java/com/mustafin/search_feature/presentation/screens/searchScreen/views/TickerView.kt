package com.mustafin.search_feature.presentation.screens.searchScreen.views

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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.mustafin.search_feature.R
import com.mustafin.search_feature.utils.search.SearchTickerModel

/* View с информацией о тикере, которая отображается при поиске */
@Composable
fun TickerView(ticker: SearchTickerModel) {
    val costColor = colorResource(id = if (ticker.isRising) R.color.green else R.color.red)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = ticker.logo,
            contentDescription = null,
            modifier = Modifier
                .size(28.dp)
                .clip(RoundedCornerShape(4.dp))
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = "${ticker.name} (${ticker.symbol})",
            style = MaterialTheme.typography.titleMedium,
            color = colorResource(id = R.color.content),
            modifier = Modifier.weight(1f)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = ticker.cost.toString(),
            style = MaterialTheme.typography.labelMedium,
            color = costColor
        )

        Spacer(modifier = Modifier.width(4.dp))

        Text(
            text = "${if (ticker.isRising) "+" else "-"}${ticker.percentDifference}%",
            style = MaterialTheme.typography.labelSmall,
            color = costColor
        )
    }
}