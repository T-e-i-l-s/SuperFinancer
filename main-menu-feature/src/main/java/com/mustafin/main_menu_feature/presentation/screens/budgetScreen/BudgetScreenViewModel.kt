package com.mustafin.main_menu_feature.presentation.screens.budgetScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mustafin.analytics.data.appMetrica.eventConstructor.EventConstructor
import com.mustafin.core.utils.budget.BudgetGoalModel
import com.mustafin.core.utils.budget.BudgetGoalStatus
import com.mustafin.core.utils.budget.BudgetInfoModel
import com.mustafin.core.utils.budget.BudgetOperationModel
import com.mustafin.main_menu_feature.data.repository.budgetRepository.BudgetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/* ViewModel экрана финансовго планировщика */
@HiltViewModel
class BudgetScreenViewModel @Inject constructor(
    private val budgetRepository: BudgetRepository,
    private val eventConstructor: EventConstructor
) : ViewModel() {
    private val _budgetInfo = MutableStateFlow<BudgetInfoModel?>(null)
    val budgetInfo: StateFlow<BudgetInfoModel?> = _budgetInfo

    private val _allGoalsList = MutableStateFlow<List<BudgetGoalModel>?>(null)
    val allGoalsList: StateFlow<List<BudgetGoalModel>?> = _allGoalsList

    private val _activeGoalsList = MutableStateFlow<List<BudgetGoalModel>?>(null)
    val activeGoalsList: StateFlow<List<BudgetGoalModel>?> = _activeGoalsList

    private val _operationsList = MutableStateFlow<List<BudgetOperationModel>?>(null)
    val operationsList: StateFlow<List<BudgetOperationModel>?> = _operationsList

    private val _showCreateGoalDialog = MutableStateFlow(false)
    val showCreateGoalDialog: StateFlow<Boolean> = _showCreateGoalDialog

    private val _showCreateTopUpOperationDialog = MutableStateFlow(false)
    val showCreateTopUpOperationDialog: StateFlow<Boolean> = _showCreateTopUpOperationDialog

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            val fullBudgetInfo = budgetRepository.getFullBudgetInfo()
            _budgetInfo.value = fullBudgetInfo.first
            _allGoalsList.value = fullBudgetInfo.second
            _activeGoalsList.value =
                fullBudgetInfo.second.filter { it.status == BudgetGoalStatus.ACTIVE }
            _operationsList.value = fullBudgetInfo.third
        }
    }

    fun createGoal(goal: BudgetGoalModel) {
        viewModelScope.launch {
            hideCreateGoalDialog()
            budgetRepository.addGoal(goal)
            loadData()
            eventConstructor.sendEvent("budget_goal_created")
        }
    }

    fun createOperation(operationModel: BudgetOperationModel) {
        viewModelScope.launch {
            hideCreateTopUpOperationDialog()
            budgetRepository.addOperation(operationModel)
            loadData()
            eventConstructor.sendEvent("bedget_operation_created")
        }
    }

    fun removeGoal(goal: BudgetGoalModel) {
        viewModelScope.launch {
            budgetRepository.deleteGoal(goal)
            loadData()
            eventConstructor.sendEvent("budget_goal_deleted")
        }
    }

    fun showCreateGoalDialog() {
        _showCreateGoalDialog.value = true
    }

    fun hideCreateGoalDialog() {
        _showCreateGoalDialog.value = false
    }

    fun showCreateTopUpOperationDialog() {
        _showCreateTopUpOperationDialog.value = true
    }

    fun hideCreateTopUpOperationDialog() {
        _showCreateTopUpOperationDialog.value = false
    }
}