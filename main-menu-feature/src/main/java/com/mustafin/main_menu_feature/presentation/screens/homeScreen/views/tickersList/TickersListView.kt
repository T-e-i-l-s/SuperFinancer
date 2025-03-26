package com.mustafin.main_menu_feature.presentation.screens.homeScreen.views.tickersList

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mustafin.core.utils.LoadingState
import com.mustafin.core.utils.tickers.TickerModel
import com.mustafin.main_menu_feature.R
import com.mustafin.main_menu_feature.presentation.screens.homeScreen.views.skeletons.TickersListSkeleton

/* View списка тикеров */
@Composable
fun TickersListView(
    tickersList: List<TickerModel>?,
    loadingState: LoadingState,
    reloadTickers: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }

    Column(Modifier.animateContentSize()) {
        tickersList?.let {
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null,
                        onClick = reloadTickers
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                when (loadingState) {
                    LoadingState.LOADING -> {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .size(16.dp)
                                .padding(2.dp),
                            color = colorResource(id = R.color.gray),
                            trackColor = Color.Transparent,
                            strokeWidth = 1.dp
                        )
                    }

                    else -> {
                        Icon(
                            painter = painterResource(id = R.drawable.refresh_cw),
                            contentDescription = null,
                            modifier = Modifier.size(16.dp),
                            tint = colorResource(id = R.color.gray)
                        )
                    }
                }

                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text = stringResource(id = R.string.refresh),
                    style = MaterialTheme.typography.labelSmall,
                    color = colorResource(id = R.color.gray)
                )
            }
        }

        Spacer(modifier = Modifier.height(4.dp))

        tickersList?.let { tickersListSafe ->
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(tickersListSafe) { ticker ->
                    TickerView(ticker = ticker)
                }
            }
        } ?: if (loadingState == LoadingState.ERROR) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.loading_error),
                    style = MaterialTheme.typography.labelMedium,
                    color = colorResource(id = R.color.gray),
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        } else {
            TickersListSkeleton()
        }
    }
}