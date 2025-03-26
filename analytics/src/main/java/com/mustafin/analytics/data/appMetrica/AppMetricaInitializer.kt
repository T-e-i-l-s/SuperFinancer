package com.mustafin.analytics.data.appMetrica

import android.content.Context
import com.mustafin.analytics.Keys
import io.appmetrica.analytics.AppMetrica
import io.appmetrica.analytics.AppMetricaConfig

object AppMetricaInitializer {
    fun initialize(context: Context) {
        val config = AppMetricaConfig.newConfigBuilder(Keys.APP_METRICA_KEY).build()
        AppMetrica.activate(context, config)
    }
}