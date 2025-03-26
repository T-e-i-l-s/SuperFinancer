package com.mustafin.search_feature

import com.mustafin.network.NetworkConstants

object SearchSettings {
    const val AI_SEARCH_MODEL_URI =
        "gpt://${NetworkConstants.YANDEX_CLOUD_CATALOG_ID}/yandexgpt-lite/latest"
    const val AI_SEARCH_PROMPT = "Обработай поисковой запрос пользователя и дай краткий ответ"
}