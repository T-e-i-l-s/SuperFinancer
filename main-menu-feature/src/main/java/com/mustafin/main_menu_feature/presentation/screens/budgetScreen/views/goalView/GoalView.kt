package com.mustafin.main_menu_feature.presentation.screens.budgetScreen.views.goalView

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import com.mustafin.core.utils.budget.BudgetGoalModel
import com.mustafin.core.utils.budget.BudgetGoalStatus
import com.mustafin.main_menu_feature.R
import java.math.BigDecimal

/* View с информацией о цели */
@Composable
fun GoalView(goal: BudgetGoalModel, removeGoal: () -> Unit) {
    HorizontalDivider(
        modifier = Modifier.fillMaxWidth(),
        thickness = 1.dp,
        color = colorResource(id = R.color.secondary_background)
    )

    Column(
        Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = goal.name,
                    style = MaterialTheme.typography.titleMedium,
                    color = colorResource(id = R.color.content)
                )

                goal.deadline?.let { deadlineSafe ->
                    Spacer(modifier = Modifier.width(4.dp))

                    Text(
                        text = stringResource(id = R.string.goal_deadline_string, deadlineSafe),
                        style = MaterialTheme.typography.titleMedium,
                        color = colorResource(id = R.color.gray)
                    )
                }
            }

            Text(
                text = stringResource(id = R.string.delete_goal),
                style = MaterialTheme.typography.labelSmall,
                color = colorResource(id = R.color.dark_red),
                modifier = Modifier.clickable(onClick = removeGoal)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        GoalProgressBar(goalProgress = goal.percentCompleted)

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = goal.currentValue.toString(),
                style = MaterialTheme.typography.labelSmall,
                color = colorResource(id = R.color.gray)
            )

            Text(
                text = goal.goal.toString(),
                style = MaterialTheme.typography.labelSmall,
                color = colorResource(id = R.color.gray)
            )
        }
    }
}

@Composable
@Preview
private fun Preview() {
    GoalView(
        goal = BudgetGoalModel(
            0,
            LoremIpsum(2).values.first(),
            BigDecimal.ONE,
            BigDecimal.ONE,
            0.5f,
            null,
            BudgetGoalStatus.ACTIVE
        )
    ) {}
}