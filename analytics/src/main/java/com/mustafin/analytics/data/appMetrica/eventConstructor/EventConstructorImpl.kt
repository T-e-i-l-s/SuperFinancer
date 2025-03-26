package com.mustafin.analytics.data.appMetrica.eventConstructor

import io.appmetrica.analytics.AppMetrica

/* Класс, которые создает кастомные события в дашборде */
class EventConstructorImpl: EventConstructor {
    override fun sendEvent(title: String, body: String?) {
        AppMetrica.reportEvent(title, body)
    }
}