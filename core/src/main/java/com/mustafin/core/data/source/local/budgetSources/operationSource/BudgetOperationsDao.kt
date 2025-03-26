package com.mustafin.core.data.source.local.budgetSources.operationSource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mustafin.core.data.source.local.database.Tables

/* Dao-интерфейс таблицы со списком целей в финансовом планировщике */
@Dao
interface BudgetOperationsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOperation(operation: BudgetOperationsEntity)

    @Query("SELECT * FROM ${Tables.BUDGET_OPERATIONS_TABLE} ORDER BY id DESC")
    suspend fun getAllOperations(): List<BudgetOperationsEntity>
}