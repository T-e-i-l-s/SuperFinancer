package com.mustafin.feed.data.source.local.favoritesSource

import android.content.SharedPreferences
import org.json.JSONArray
import javax.inject.Inject

/* Класс, который безопасно сохраняет и получает данные о избранном */
class FavoritesSource @Inject constructor(
    private val encryptedSharedPreferences: SharedPreferences
) {
    companion object {
        const val FAVORITES_KEY = "favorite_ids"
    }

    fun saveFavoriteIds(ids: List<Int>) {
        val jsonArray = JSONArray(ids).toString()
        encryptedSharedPreferences.edit().apply {
            putString(FAVORITES_KEY, jsonArray)
            apply()
        }
    }

    fun getFavoriteIds(): List<Int> {
        val jsonString = encryptedSharedPreferences.getString(FAVORITES_KEY, "[]") ?: "[]"
        val jsonArray = JSONArray(jsonString)
        return List(jsonArray.length()) { jsonArray.getInt(it) }
    }
}