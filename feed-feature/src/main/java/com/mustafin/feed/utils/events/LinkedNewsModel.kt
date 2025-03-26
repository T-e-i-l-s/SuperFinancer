package com.mustafin.feed.utils.events

/* Модель новости, которая может быть прикреплена к событию, создаваемому пользователем */
data class LinkedNewsModel(
    val title: String,
    val link: String,
    val imageUrl: String?,
)
