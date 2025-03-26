package com.mustafin.main_menu_feature.data.repository.budgetRepository

import com.mustafin.core.utils.budget.BudgetGoalModel
import com.mustafin.core.utils.budget.BudgetInfoModel
import com.mustafin.core.utils.budget.BudgetOperationModel

interface BudgetRepository {
    suspend fun getFullBudgetInfo()
            : Triple<BudgetInfoModel, List<BudgetGoalModel>, List<BudgetOperationModel>>

    suspend fun deleteGoal(goal: BudgetGoalModel)

    suspend fun addOperation(operationModel: BudgetOperationModel)

    suspend fun addGoal(goal: BudgetGoalModel)
}