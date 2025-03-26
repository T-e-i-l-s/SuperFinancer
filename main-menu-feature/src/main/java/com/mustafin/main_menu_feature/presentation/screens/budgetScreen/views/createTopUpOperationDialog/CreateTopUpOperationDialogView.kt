package com.mustafin.main_menu_feature.presentation.screens.budgetScreen.views.createTopUpOperationDialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mustafin.core.utils.budget.BudgetGoalModel
import com.mustafin.core.utils.budget.BudgetOperationModel
import com.mustafin.main_menu_feature.R
import com.mustafin.ui_components.presentation.buttons.CustomTinyButton
import com.mustafin.ui_components.presentation.inputs.CustomTextField

/* View с всплывающим окном для создания операции пополнения счета */
@Composable
fun CreateTopUpOperationDialogView(
    show: Boolean,
    createGoal: (BudgetOperationModel) -> Unit,
    dismiss: () -> Unit,
    goals: List<BudgetGoalModel>?,
    viewModel: CreateTopUpOperationDialogViewModel = viewModel()
) {
    val operationValue = viewModel.operationValue.collectAsStateWithLifecycle()
    val description = viewModel.description.collectAsStateWithLifecycle()
    val enableToCreate = viewModel.enableToCreate.collectAsStateWithLifecycle()

    if (show) {
        Dialog(onDismissRequest = dismiss) {
            Column(
                Modifier
                    .padding(16.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(colorResource(id = R.color.background)),
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = stringResource(id = R.string.top_up),
                    style = MaterialTheme.typography.titleLarge,
                    color = colorResource(id = R.color.content),
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                CustomTextField(
                    value = operationValue.value.toString(),
                    onValueChange = viewModel::setOperationValue,
                    placeholder = stringResource(id = R.string.sum),
                    keyboardType = KeyboardType.Number,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                CustomTextField(
                    value = description.value,
                    onValueChange = viewModel::setDescription,
                    placeholder = stringResource(id = R.string.goal_description),
                    keyboardType = KeyboardType.Text,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                goals?.let { goalsSafe ->
                    GoalSelectorView(goals = goalsSafe, onSelectGoal = viewModel::setSelectedGoalId)
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    CustomTinyButton(
                        text = stringResource(id = R.string.cancel),
                        onClick = dismiss,
                        contentColor = colorResource(id = R.color.content),
                        backgroundColor = colorResource(id = R.color.secondary_background)
                    )

                    CustomTinyButton(
                        text = stringResource(id = R.string.add),
                        onClick = {
                            createGoal(viewModel.provideBudgetOperationModelAndCleanState())
                        },
                        enabled = enableToCreate.value
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}
