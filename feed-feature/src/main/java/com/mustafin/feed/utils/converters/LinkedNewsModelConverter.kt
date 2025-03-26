package com.mustafin.feed.utils.converters

import androidx.room.TypeConverter
import com.mustafin.feed.utils.events.LinkedNewsModel
import org.json.JSONObject

/* Конвертер для класса LinkedNewsModel */
class LinkedNewsModelConverter {
    @TypeConverter
    fun fromLinkedNewsModel(model: LinkedNewsModel?): String? {
        return model?.let {
            val jsonObject = JSONObject()
            jsonObject.put("title", it.title)
            jsonObject.put("link", it.link)
            jsonObject.put("imageUrl", it.imageUrl)
            jsonObject.toString()
        }
    }

    @TypeConverter
    fun toLinkedNewsModel(data: String): LinkedNewsModel {
        val jsonObject = JSONObject(data)
        val title = jsonObject.optString("title", "")
        val link = jsonObject.optString("link", "")
        val imageUrl = jsonObject.optString("imageUrl", "")
        return LinkedNewsModel(title, link, imageUrl)
    }
}

