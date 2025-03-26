package com.mustafin.core.data.source.local.budgetSources.goalsSource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mustafin.core.data.source.local.database.Tables
import com.mustafin.core.utils.budget.BudgetGoalStatus
import java.math.BigDecimal

/* Dao-интерфейс таблицы со списком целей в финансовом планировщике */
@Dao
interface BudgetGoalsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGoal(goal: BudgetGoalsEntity)

    @Query("UPDATE ${Tables.BUDGET_GOALS_TABLE} SET status = :status WHERE id = :goalId")
    suspend fun changeGoalStatus(goalId: Int, status: BudgetGoalStatus)

    @Query("SELECT * FROM ${Tables.BUDGET_GOALS_TABLE}")
    suspend fun getAllGoals(): List<BudgetGoalsEntity>

    @Query("SELECT * FROM ${Tables.BUDGET_GOALS_TABLE} WHERE id = :goalId")
    suspend fun getGoalById(goalId: Int): BudgetGoalsEntity

    @Query("UPDATE ${Tables.BUDGET_GOALS_TABLE} SET currentValue = :newValue WHERE id = :goalId")
    suspend fun updateGoalValue(goalId: Int, newValue: BigDecimal)
}