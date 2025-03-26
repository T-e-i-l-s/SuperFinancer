package com.mustafin.core.utils.extensions

import java.time.LocalDateTime

/* Файл с экстеншнами класснов LocalDate и LocalDateTime */

fun LocalDateTime.toRuZoneDateFormat(): String {
    val formatter = java.time.format.DateTimeFormatter.ofPattern("dd.MM.yyyy")
    return this.format(formatter)
}