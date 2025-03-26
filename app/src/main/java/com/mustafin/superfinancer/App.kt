package com.mustafin.superfinancer

import android.app.Application
import com.mustafin.analytics.data.appMetrica.AppMetricaInitializer
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App: Application(){

    override fun onCreate() {
        super.onCreate()
        // Инициализируем Yandex AppMetrica для сбора метрик приложения
        AppMetricaInitializer.initialize(this)
    }
}