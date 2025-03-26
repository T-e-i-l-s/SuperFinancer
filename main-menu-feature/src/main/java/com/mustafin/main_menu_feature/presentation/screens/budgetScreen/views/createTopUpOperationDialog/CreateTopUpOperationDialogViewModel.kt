package com.mustafin.main_menu_feature.presentation.screens.budgetScreen.views.createTopUpOperationDialog

import androidx.lifecycle.ViewModel
import com.mustafin.core.utils.budget.BudgetOperationModel
import com.mustafin.core.utils.budget.BudgetOperationType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.math.BigDecimal

/* ViewModel всплывающего окна для создания операции со счетом */
class CreateTopUpOperationDialogViewModel : ViewModel() {
    private val _operationValue = MutableStateFlow(BigDecimal.ZERO)
    val operationValue: StateFlow<BigDecimal> = _operationValue

    private var selectedGoalId: Int? = null

    private val _description = MutableStateFlow("")
    val description: StateFlow<String> = _description

    private val _enableToCreate = MutableStateFlow(false)
    val enableToCreate: StateFlow<Boolean> = _enableToCreate

    fun setOperationValue(operationValue: String) {
        try {
            _operationValue.value = BigDecimal(operationValue)
        } catch (e: Exception) {
            _operationValue.value = BigDecimal.ZERO
        }
        checkIsCreateEnabled()
    }

    fun setSelectedGoalId(goalId: Int) {
        selectedGoalId = goalId
        checkIsCreateEnabled()
    }

    fun setDescription(description: String) {
        _description.value = description
    }

    private fun checkIsCreateEnabled() {
        _enableToCreate.value = operationValue.value != BigDecimal.ZERO && selectedGoalId != null
    }

    // Фунцкия, которая из введенных данных формирует BudgetOperationModel и после очищает все поля
    fun provideBudgetOperationModelAndCleanState(): BudgetOperationModel {
        val budgetOperationModel = BudgetOperationModel(
            value = operationValue.value,
            type = BudgetOperationType.TOP_UP,
            goalId = selectedGoalId ?: -1,
            description = description.value.ifBlank { null }
        )

        // Сброс значений
        _operationValue.value = BigDecimal.ZERO
        _description.value = ""
        _enableToCreate.value = false
        selectedGoalId = null

        return budgetOperationModel
    }
}