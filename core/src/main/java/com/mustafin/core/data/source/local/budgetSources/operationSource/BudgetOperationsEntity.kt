package com.mustafin.core.data.source.local.budgetSources.operationSource

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mustafin.core.data.source.local.database.Tables
import com.mustafin.core.utils.budget.BudgetOperationType
import java.math.BigDecimal

@Entity(tableName = Tables.BUDGET_OPERATIONS_TABLE)
data class BudgetOperationsEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val type: BudgetOperationType,
    val value: BigDecimal,
    val goalId: Int,
    val description: String?
)
