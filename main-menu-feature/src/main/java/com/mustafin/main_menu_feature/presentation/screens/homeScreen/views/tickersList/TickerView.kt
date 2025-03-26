package com.mustafin.main_menu_feature.presentation.screens.homeScreen.views.tickersList

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.mustafin.core.utils.tickers.TickerModel
import com.mustafin.main_menu_feature.R

/* View с информацие о тикере */
@Composable
fun TickerView(ticker: TickerModel) {
    val costColor =
        if (ticker.isRising) colorResource(id = R.color.green)
        else colorResource(id = R.color.red)

    Column(
        Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(colorResource(id = R.color.secondary_background))
            .padding(12.dp)
    ) {
        AsyncImage(
            model = ticker.logo,
            contentDescription = null,
            modifier = Modifier
                .size(28.dp)
                .clip(RoundedCornerShape(4.dp))
        )

        Spacer(modifier = Modifier.height(4.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = ticker.cost.toString(),
                style = MaterialTheme.typography.labelLarge,
                color = costColor
            )

            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = "${if (ticker.isRising) "+" else "-"}${ticker.percentDifference}%",
                style = MaterialTheme.typography.labelSmall,
                color = costColor
            )
        }

        Spacer(modifier = Modifier.height(2.dp))

        Text(
            text = "${ticker.name} (${ticker.symbol})",
            style = MaterialTheme.typography.titleSmall,
            color = colorResource(id = R.color.content)
        )
    }
}

@Composable
@Preview
private fun Preview() {
    TickerView(
        ticker = TickerModel(
            "TEST",
            LoremIpsum(2).values.first(),
            "",
            100f,
            true,
            10f,
            10f
        )
    )
}