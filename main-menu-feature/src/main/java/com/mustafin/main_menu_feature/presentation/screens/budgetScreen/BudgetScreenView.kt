package com.mustafin.main_menu_feature.presentation.screens.budgetScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mustafin.main_menu_feature.R
import com.mustafin.main_menu_feature.presentation.screens.budgetScreen.views.BudgetInfoView
import com.mustafin.main_menu_feature.presentation.screens.budgetScreen.views.OperationView
import com.mustafin.main_menu_feature.presentation.screens.budgetScreen.views.createGoalDialog.CreateGoalDialog
import com.mustafin.main_menu_feature.presentation.screens.budgetScreen.views.createTopUpOperationDialog.CreateTopUpOperationDialogView
import com.mustafin.main_menu_feature.presentation.screens.budgetScreen.views.goalView.GoalView
import com.mustafin.ui_components.presentation.buttons.CustomTinyButton

/* View экрана финансового планировщика */
@Composable
fun BudgetScreenView(viewModel: BudgetScreenViewModel = hiltViewModel()) {
    val budgetInfo = viewModel.budgetInfo.collectAsStateWithLifecycle()
    val allGoalsList = viewModel.allGoalsList.collectAsStateWithLifecycle()
    val activeGoalsList = viewModel.activeGoalsList.collectAsStateWithLifecycle()
    val operationsList = viewModel.operationsList.collectAsStateWithLifecycle()
    val showCreateGoalDialog = viewModel.showCreateGoalDialog.collectAsStateWithLifecycle()
    val showCreateTopUpOperationDialog =
        viewModel.showCreateTopUpOperationDialog.collectAsStateWithLifecycle()

    LazyColumn(
        Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.background)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Spacer(modifier = Modifier.height(16.dp))

            budgetInfo.value?.let { budgetInfoSafe ->
                BudgetInfoView(budgetInfoSafe)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.goals),
                    style = MaterialTheme.typography.displayLarge,
                    color = colorResource(id = R.color.content),
                )

                CustomTinyButton(
                    text = stringResource(id = R.string.add),
                    onClick = viewModel::showCreateGoalDialog,
                    icon = painterResource(id = R.drawable.plus)
                )
            }
        }


        activeGoalsList.value?.let { goalsListSafe ->
            items(goalsListSafe, key = { it.id }) { goal ->
                GoalView(goal) { viewModel.removeGoal(goal) }
            }
        }

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.operations),
                    style = MaterialTheme.typography.displayLarge,
                    color = colorResource(id = R.color.content),
                )

                CustomTinyButton(
                    text = stringResource(id = R.string.add),
                    onClick = viewModel::showCreateTopUpOperationDialog,
                    icon = painterResource(id = R.drawable.plus),
                    enabled = !activeGoalsList.value.isNullOrEmpty()
                )
            }
        }

        operationsList.value?.let { operationsListSafe ->
            items(operationsListSafe) { operation ->
                OperationView(operation, allGoalsList.value)
            }
        }
    }

    CreateGoalDialog(
        show = showCreateGoalDialog.value,
        createGoal = viewModel::createGoal,
        dismiss = viewModel::hideCreateGoalDialog
    )

    CreateTopUpOperationDialogView(
        show = showCreateTopUpOperationDialog.value,
        createGoal = viewModel::createOperation,
        dismiss = viewModel::hideCreateTopUpOperationDialog,
        goals = activeGoalsList.value
    )
}