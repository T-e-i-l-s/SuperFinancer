package com.mustafin.main_menu_feature.presentation.screens.budgetScreen.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mustafin.core.utils.budget.BudgetInfoModel
import com.mustafin.main_menu_feature.R
import java.lang.String.format
import java.math.BigDecimal
import java.util.Locale

/* View с общей информацией о счете(процент закрытых целей, общая сумма и тд) */
@Composable
fun BudgetInfoView(budgetInfo: BudgetInfoModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${budgetInfo.currentValue} ₽",
                style = MaterialTheme.typography.displayLarge,
                color = colorResource(id = R.color.content),
            )

            Text(
                text = " ≈ ${format(Locale.US, "%.1f", budgetInfo.percentCompleted * 100)}%",
                style = MaterialTheme.typography.labelLarge,
                color = colorResource(id = R.color.gray),
                modifier = Modifier.fillMaxWidth()
            )
        }

        Text(
            text = stringResource(id = R.string.total_goal, budgetInfo.totalGoal),
            style = MaterialTheme.typography.labelMedium,
            color = colorResource(id = R.color.gray),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
@Preview
private fun Preview() {
    BudgetInfoView(
        BudgetInfoModel(
            BigDecimal.ONE,
            BigDecimal.ONE,
            0.5f
        )
    )
}