package com.mustafin.core.utils.budget

import java.math.BigDecimal

/* Модель данных с общей информацией о результатах финансовго планирования */
data class BudgetInfoModel(
    val totalGoal: BigDecimal,
    val currentValue: BigDecimal,
    val percentCompleted: Float
)
