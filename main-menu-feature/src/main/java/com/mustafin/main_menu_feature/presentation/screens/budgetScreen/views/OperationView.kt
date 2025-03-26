package com.mustafin.main_menu_feature.presentation.screens.budgetScreen.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mustafin.core.utils.budget.BudgetGoalModel
import com.mustafin.core.utils.budget.BudgetOperationModel
import com.mustafin.core.utils.budget.BudgetOperationType
import com.mustafin.main_menu_feature.R
import java.math.BigDecimal

/* View с общей информацией о счете(процент закрытых целей, общая сумма и тд */
@Composable
fun OperationView(operation: BudgetOperationModel, goals: List<BudgetGoalModel>?) {
    HorizontalDivider(
        modifier = Modifier.fillMaxWidth(),
        thickness = 1.dp,
        color = colorResource(id = R.color.secondary_background)
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            Modifier
                .padding(end = 16.dp)
                .weight(1f)
        ) {
            Text(
                text = when (operation.type) {
                    BudgetOperationType.TOP_UP -> stringResource(id = R.string.top_up)
                    BudgetOperationType.WITHDRAW -> stringResource(id = R.string.withdraw)
                },
                style = MaterialTheme.typography.titleMedium,
                color = colorResource(id = R.color.content)
            )

            Spacer(modifier = Modifier.height(4.dp))

            goals?.find { it.id == operation.goalId }?.name?.let { goalName ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_right),
                        contentDescription = null,
                        modifier = Modifier.size(12.dp),
                        tint = colorResource(id = R.color.gray)
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Text(
                        text = goalName,
                        style = MaterialTheme.typography.labelMedium,
                        color = colorResource(id = R.color.gray)
                    )
                }
            }

            operation.description?.let { descriptionSafe ->
                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = descriptionSafe,
                    style = MaterialTheme.typography.labelSmall,
                    color = colorResource(id = R.color.gray)
                )
            }
        }

        when (operation.type) {
            BudgetOperationType.TOP_UP -> {
                Text(
                    text = "+${operation.value}",
                    style = MaterialTheme.typography.titleMedium,
                    color = colorResource(id = R.color.green)
                )
            }

            BudgetOperationType.WITHDRAW -> {
                Text(
                    text = "-${operation.value}",
                    style = MaterialTheme.typography.titleMedium,
                    color = colorResource(id = R.color.red)
                )
            }
        }
    }
}

@Composable
@Preview
private fun Preview() {
    OperationView(
        operation = BudgetOperationModel(
            0,
            BigDecimal.ONE,
            BudgetOperationType.TOP_UP,
            0,
            "test"
        ),
        goals = emptyList()
    )
}