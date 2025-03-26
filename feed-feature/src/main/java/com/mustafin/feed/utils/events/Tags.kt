package com.mustafin.feed.utils.events

import com.mustafin.feed.R

/* Теги для событий в ленте */
sealed class Tags(val nameRes: Int, val colorRes: Int) {
    data object Education : Tags(R.string.tag_education, R.color.tag_education_color)
    data object Lifestyle : Tags(R.string.tag_lifestyle, R.color.tag_lifestyle_color)
    data object Future : Tags(R.string.tag_future, R.color.tag_future_color)
    data object Meal : Tags(R.string.tag_meal, R.color.tag_meal_color)
    data object Travel : Tags(R.string.tag_travel, R.color.tag_travel_color)

    companion object {
        val tagsList by lazy { listOf(Education, Lifestyle, Future, Meal, Travel) }
    }
}