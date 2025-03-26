package com.mustafin.core.utils.budget

import java.math.BigDecimal

/* Модель данных об одной из целей в планнировщике финансов */
data class BudgetGoalModel(
    val id: Int = 0,
    val name: String,
    val goal: BigDecimal,
    val currentValue: BigDecimal,
    val percentCompleted: Float,
    val deadline: String? = null,
    val status: BudgetGoalStatus
)
