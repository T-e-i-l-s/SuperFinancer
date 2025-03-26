package com.mustafin.core.utils.budget

import java.math.BigDecimal

/* Модель данных транзакции в планнеровщике финансов */
data class BudgetOperationModel(
    val id: Int? = null,
    val value: BigDecimal,
    val type: BudgetOperationType,
    val goalId: Int,
    val description: String? = null
)
