package com.mustafin.core.utils.typeConverters

import androidx.room.TypeConverter
import com.mustafin.core.utils.budget.BudgetOperationType

/* Конвертеры BudgetOperationType */
class BudgetOperationTypeConverters {
    @TypeConverter
    fun fromBudgetOperationType(type: BudgetOperationType?): String? {
        return type?.name
    }

    @TypeConverter
    fun toBudgetOperationType(typeString: String?): BudgetOperationType? {
        return typeString?.let { BudgetOperationType.valueOf(it) }
    }
}