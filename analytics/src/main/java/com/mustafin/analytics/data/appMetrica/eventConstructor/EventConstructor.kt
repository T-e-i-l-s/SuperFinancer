package com.mustafin.analytics.data.appMetrica.eventConstructor

interface EventConstructor {
    fun sendEvent(title: String, body: String? = null)
}