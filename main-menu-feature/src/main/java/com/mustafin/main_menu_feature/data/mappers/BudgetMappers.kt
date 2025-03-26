package com.mustafin.main_menu_feature.data.mappers

import com.mustafin.core.data.source.local.budgetSources.goalsSource.BudgetGoalsEntity
import com.mustafin.core.data.source.local.budgetSources.operationSource.BudgetOperationsEntity
import com.mustafin.core.utils.budget.BudgetGoalModel
import com.mustafin.core.utils.budget.BudgetOperationModel

/* Мапперы моделей данных финансового планировщика */

fun BudgetOperationsEntity.mapToBudgetOperationModel(): BudgetOperationModel {
    return BudgetOperationModel(
        id = id,
        value = value,
        type = type,
        goalId = goalId,
        description = description
    )
}

fun BudgetOperationModel.mapToBudgetOperationsEntity(): BudgetOperationsEntity {
    return BudgetOperationsEntity(
        type = type,
        value = value,
        goalId = goalId,
        description = description
    )
}

fun BudgetGoalsEntity.mapToBudgetGoalModel(): BudgetGoalModel {
    val percentCompleted = if (goal.toFloat() == 0f) {
        0f
    } else {
        currentValue.toFloat() / goal.toFloat()
    }

    return BudgetGoalModel(
        id = id,
        name = name,
        goal = goal,
        currentValue = currentValue,
        percentCompleted = percentCompleted,
        deadline = deadline,
        status = status
    )
}

fun BudgetGoalModel.mapToBudgetGoalsEntity(): BudgetGoalsEntity {
    return BudgetGoalsEntity(
        name = name,
        currentValue = currentValue,
        goal = goal,
        deadline = deadline,
        status = status
    )
}