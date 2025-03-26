package com.mustafin.main_menu_feature.presentation.screens.budgetScreen.views.createTopUpOperationDialog

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.mustafin.core.utils.budget.BudgetGoalModel
import com.mustafin.main_menu_feature.R
import kotlinx.coroutines.launch
import okhttp3.internal.format

/* View для выбора цели из предложенных */
@Composable
fun GoalSelectorView(goals: List<BudgetGoalModel>, onSelectGoal: (Int) -> Unit) {
    val pagerState = rememberPagerState(pageCount = { goals.size })
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(pagerState.currentPage) {
        onSelectGoal(goals[pagerState.currentPage].id)
    }

    Box(
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .border(
                    1.dp,
                    color = colorResource(id = R.color.additional),
                    RoundedCornerShape(16.dp)
                )
        ) { index ->
            onSelectGoal(goals[index].id)
            GoalSelectorItemView(goal = goals[index])
        }

        if (pagerState.currentPage > 0) {
            IconNavigation(
                iconRes = R.drawable.chevron_left,
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                coroutineScope.launch {
                    pagerState.animateScrollToPage(pagerState.currentPage - 1)
                }
            }
        }

        if (pagerState.currentPage < goals.size - 1) {
            IconNavigation(
                iconRes = R.drawable.chevron_right,
                modifier = Modifier.align(Alignment.CenterEnd)
            ) {
                coroutineScope.launch {
                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                }
            }
        }
    }
}

/* View с краткой информацией о цели */
@Composable
private fun GoalSelectorItemView(goal: BudgetGoalModel) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp, horizontal = 32.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = goal.name,
            style = MaterialTheme.typography.titleMedium,
            color = colorResource(id = R.color.content),
            modifier = Modifier.weight(1f),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Text(
            text = "${format("%.1f", goal.percentCompleted * 100)}%",
            style = MaterialTheme.typography.titleMedium,
            color = colorResource(id = R.color.content)
        )
    }
}


@Composable
private fun IconNavigation(iconRes: Int, modifier: Modifier, onClick: () -> Unit) {
    Icon(
        painter = painterResource(id = iconRes),
        contentDescription = null,
        tint = colorResource(id = R.color.content),
        modifier = modifier
            .padding(4.dp)
            .clip(CircleShape)
            .size(16.dp)
            .clickable(onClick = onClick)
    )
}