package com.mustafin.core.utils.typeConverters

import androidx.room.TypeConverter
import java.math.BigDecimal

/* Конвертеры BigDecimal */
class BigDecimalConverters {
    @TypeConverter
    fun fromBigDecimal(value: BigDecimal?): String? {
        return value?.toPlainString()
    }

    @TypeConverter
    fun toBigDecimal(value: String?): BigDecimal? {
        return value?.let { BigDecimal(it) }
    }
}