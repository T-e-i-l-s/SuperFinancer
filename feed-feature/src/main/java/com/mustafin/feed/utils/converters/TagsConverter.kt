package com.mustafin.feed.utils.converters

import androidx.room.TypeConverter
import com.mustafin.feed.utils.events.Tags

/* Конвертер для класса Tags */
import org.json.JSONArray
import org.json.JSONObject

class TagsConverter {
    @TypeConverter
    fun fromTagsList(tags: List<Tags>): String {
        val jsonArray = JSONArray()
        tags.forEach {
            val jsonObject = JSONObject()
            jsonObject.put("tag", it::class.simpleName ?: "")
            jsonArray.put(jsonObject)
        }
        return jsonArray.toString()
    }

    @TypeConverter
    fun toTagsList(data: String): List<Tags> {
        val jsonArray = JSONArray(data)
        val list = mutableListOf<Tags>()
        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
            val tagName = jsonObject.getString("tag")
            when (tagName) {
                "Education" -> list.add(Tags.Education)
                "Lifestyle" -> list.add(Tags.Lifestyle)
                "Future" -> list.add(Tags.Future)
                "Meal" -> list.add(Tags.Meal)
                "Travel" -> list.add(Tags.Travel)
            }
        }
        return list
    }
}