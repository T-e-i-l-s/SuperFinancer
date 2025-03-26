package com.mustafin.core.data.source.local.budgetSources.goalsSource

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mustafin.core.data.source.local.database.Tables
import com.mustafin.core.utils.budget.BudgetGoalStatus
import java.math.BigDecimal

@Entity(tableName = Tables.BUDGET_GOALS_TABLE)
data class BudgetGoalsEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val currentValue: BigDecimal,
    val goal: BigDecimal,
    val deadline: String?,
    val status: BudgetGoalStatus
)
