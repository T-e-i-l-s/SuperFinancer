package com.mustafin.network.data.source.llmApi

import com.mustafin.network.Keys
import com.mustafin.network.NetworkConstants
import com.mustafin.network.domain.exceptions.FailedToFetchDataException
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import javax.inject.Inject

/* Класс для работы с при ллм(в нашем случае Yandex GPT) */
class LLMApiService @Inject constructor(
    private val httpClient: HttpClient
) {
    companion object {
        const val IAM_TOKEN_ENDPOINT =
            "${NetworkConstants.YANDEX_CLOUD_IAM_API_BASE_URL}/tokens"
        const val ASK_LLM_ENDPOINT =
            "${NetworkConstants.YANDEX_CLOUD_LLM_API_BASE_URL}/completion"
    }

    // При работе с Yandex GPT требуется генерация iam токена, который действует 12 часов
    suspend fun getIAMToken(): Result<String> {
        return try {
            val response = httpClient.post(IAM_TOKEN_ENDPOINT) {
                contentType(ContentType.Application.Json)
                setBody(GetIAMTokenRequestModel(Keys.YANDEX_CLOUD_API_KEY))
            }
            Result.success((response.body() as GetIAMTokenResponseModel).iamToken)
        } catch (e: Exception) {
            Result.failure(FailedToFetchDataException())
        }
    }

    suspend fun askLLM(
        iamToken: String,
        responseBody: AskLLMRequestModel
    ): Result<AskLLMResponseModel> {
        return try {
            val response = httpClient.post(ASK_LLM_ENDPOINT) {
                contentType(ContentType.Application.Json)
                header("Authorization", "Bearer $iamToken")
                setBody(responseBody)
            }
            Result.success(response.body() as AskLLMResponseModel)
        } catch (e: Exception) {
            Result.failure(FailedToFetchDataException())
        }
    }
}