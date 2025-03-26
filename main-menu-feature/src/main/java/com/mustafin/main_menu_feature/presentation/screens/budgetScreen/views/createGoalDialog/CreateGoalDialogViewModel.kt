package com.mustafin.main_menu_feature.presentation.screens.budgetScreen.views.createGoalDialog

import androidx.lifecycle.ViewModel
import com.mustafin.core.utils.budget.BudgetGoalModel
import com.mustafin.core.utils.budget.BudgetGoalStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.math.BigDecimal

/* ViewModel всплывающего окна для создания цели */
class CreateGoalDialogViewModel : ViewModel() {
    private val _goalName = MutableStateFlow("")
    val goalName: StateFlow<String> = _goalName

    private val _goalValue = MutableStateFlow(BigDecimal.ZERO)
    val goalValue: StateFlow<BigDecimal> = _goalValue

    private val _deadline = MutableStateFlow("")
    val deadline: StateFlow<String> = _deadline

    private val _enableToCreate = MutableStateFlow(false)
    val enableToCreate: StateFlow<Boolean> = _enableToCreate

    fun setGoalName(goalName: String) {
        _goalName.value = goalName
        checkIsCreateEnabled()
    }

    fun setGoalValue(goalValue: String) {
        try {
            _goalValue.value = BigDecimal(goalValue)
        } catch (e: Exception) {
            _goalValue.value = BigDecimal.ZERO
        }
        checkIsCreateEnabled()
    }

    fun setDeadline(deadline: String) {
        _deadline.value = deadline
        checkIsCreateEnabled()
    }

    private fun checkIsCreateEnabled() {
        _enableToCreate.value = goalName.value.isNotEmpty() && goalValue.value != BigDecimal.ZERO
    }

    // Фунцкия, которая из введенных данных формирует BudgetGoalModel и после очищает все поля
    fun provideBudgetGoalModelAndCleanState(): BudgetGoalModel {
        val budgetGoalModel = BudgetGoalModel(
            name = goalName.value,
            goal = goalValue.value,
            currentValue = BigDecimal.ZERO,
            percentCompleted = 0f,
            deadline = deadline.value.ifBlank { null },
            status = BudgetGoalStatus.ACTIVE
        )

        // Сброс значений
        _goalName.value = ""
        _goalValue.value = BigDecimal.ZERO
        _deadline.value = ""
        _enableToCreate.value = false

        return budgetGoalModel
    }
}