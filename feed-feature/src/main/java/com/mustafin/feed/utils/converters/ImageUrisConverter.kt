package com.mustafin.feed.utils.converters

import androidx.room.TypeConverter
import org.json.JSONArray

/* Конвертер для List<String> */
class ImageUrisConverter {
    @TypeConverter
    fun fromStringsList(value: List<String>): String {
        val jsonArray = JSONArray()
        value.forEach { jsonArray.put(it) }
        return jsonArray.toString()
    }

    @TypeConverter
    fun toStringsList(value: String): List<String> {
        val jsonArray = JSONArray(value)
        val list = mutableListOf<String>()
        for (i in 0 until jsonArray.length()) {
            list.add(jsonArray.getString(i))
        }
        return list
    }
}