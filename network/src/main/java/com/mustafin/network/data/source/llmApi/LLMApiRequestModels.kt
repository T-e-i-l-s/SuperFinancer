package com.mustafin.network.data.source.llmApi

import kotlinx.serialization.Serializable

/* Модели тел запросов при обащении к api Yandex GPT */

@Serializable
data class GetIAMTokenRequestModel(val yandexPassportOauthToken: String)

@Serializable
data class AskLLMRequestModel(
    val modelUri: String,
    val messages: List<LLMMessageRequestModel>
)

@Serializable
data class LLMMessageRequestModel(
    val role: String,
    val text: String
)
