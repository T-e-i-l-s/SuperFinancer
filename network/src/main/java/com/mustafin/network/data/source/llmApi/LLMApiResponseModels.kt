package com.mustafin.network.data.source.llmApi

import kotlinx.serialization.Serializable

/* Модели ответов api Yandex GPT */

@Serializable
data class GetIAMTokenResponseModel(val iamToken: String)

@Serializable
data class AskLLMResponseModel(
    val result: LLMResultResponseModel
)

@Serializable
data class LLMResultResponseModel(
    val alternatives: List<LLMAlternativeResponseModel>
)

@Serializable
data class LLMAlternativeResponseModel(val message: LLMMessageResponseModel)

@Serializable
data class LLMMessageResponseModel(
    val role: String,
    val text: String
)