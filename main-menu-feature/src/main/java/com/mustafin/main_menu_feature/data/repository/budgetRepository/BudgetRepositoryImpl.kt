package com.mustafin.main_menu_feature.data.repository.budgetRepository

import com.mustafin.core.data.source.local.budgetSources.goalsSource.BudgetGoalsDao
import com.mustafin.core.data.source.local.budgetSources.operationSource.BudgetOperationsDao
import com.mustafin.core.utils.budget.BudgetGoalModel
import com.mustafin.core.utils.budget.BudgetGoalStatus
import com.mustafin.core.utils.budget.BudgetInfoModel
import com.mustafin.core.utils.budget.BudgetOperationModel
import com.mustafin.core.utils.budget.BudgetOperationType
import com.mustafin.main_menu_feature.data.mappers.mapToBudgetGoalModel
import com.mustafin.main_menu_feature.data.mappers.mapToBudgetGoalsEntity
import com.mustafin.main_menu_feature.data.mappers.mapToBudgetOperationModel
import com.mustafin.main_menu_feature.data.mappers.mapToBudgetOperationsEntity
import java.math.BigDecimal
import javax.inject.Inject

/* Класс репозитория для работы с финансовым планеровщиком */
class BudgetRepositoryImpl @Inject constructor(
    private val goalsDao: BudgetGoalsDao,
    private val operationsDao: BudgetOperationsDao
) : BudgetRepository {
    /*
    Функция возвращает 3 элемента в виде Triple:
    1. Общую информацию о финансах
    2. Цели
    3. Операции по счету
    */
    override suspend fun getFullBudgetInfo(): Triple<BudgetInfoModel, List<BudgetGoalModel>, List<BudgetOperationModel>> {
        val goals = goalsDao.getAllGoals().map { it.mapToBudgetGoalModel() }
        val operations = operationsDao.getAllOperations().map {
            it.mapToBudgetOperationModel()
        }

        val activeGoals = goals.filter { it.status == BudgetGoalStatus.ACTIVE }
        val totalAmount = activeGoals.sumOf { it.currentValue }
        val totalGoal = activeGoals.sumOf { it.goal }

        val totalPercentCompleted =
            (totalAmount.toFloat() / totalGoal.toFloat()).takeIf { totalGoal.toFloat() != 0f } ?: 0f

        return Triple(
            BudgetInfoModel(
                totalGoal,
                totalAmount,
                totalPercentCompleted
            ),
            goals,
            operations
        )
    }

    override suspend fun deleteGoal(goal: BudgetGoalModel) {
        if (goal.currentValue != BigDecimal.ZERO) {
            operationsDao.insertOperation(
                BudgetOperationModel(
                    type = BudgetOperationType.WITHDRAW,
                    value = goal.currentValue,
                    goalId = goal.id
                ).mapToBudgetOperationsEntity()
            )
        }
        goalsDao.changeGoalStatus(goal.id, BudgetGoalStatus.DELETED)
    }

    override suspend fun addOperation(operationModel: BudgetOperationModel) {
        val operationSum: BigDecimal
        if (operationModel.type == BudgetOperationType.TOP_UP) {
            val goal = goalsDao.getGoalById(operationModel.goalId)
            val newValue = (goal.currentValue + operationModel.value).coerceAtMost(goal.goal)
            if (goal.goal - goal.currentValue == BigDecimal.ZERO) return
            operationSum = (goal.goal - goal.currentValue).coerceAtMost(operationModel.value)
            goalsDao.updateGoalValue(operationModel.goalId, newValue)
        } else {
            val goal = goalsDao.getGoalById(operationModel.goalId)
            val newValue = (goal.currentValue - operationModel.value).coerceAtLeast(goal.goal)
            operationSum = goal.currentValue.coerceAtMost(operationModel.value)
            goalsDao.updateGoalValue(operationModel.goalId, newValue)
        }
        operationsDao.insertOperation(
            operationModel.copy(value = operationSum).mapToBudgetOperationsEntity()
        )
    }

    override suspend fun addGoal(goal: BudgetGoalModel) {
        goalsDao.insertGoal(goal.mapToBudgetGoalsEntity())
    }
}