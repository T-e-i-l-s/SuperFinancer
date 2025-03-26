package com.mustafin.superfinancer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.mustafin.navigation.presentation.AppNavGraph
import com.mustafin.superfinancer.ui.theme.SuperFinancerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(statusBarStyle = SystemBarStyle.auto(0, 0))
        setContent {
            SuperFinancerTheme {
                AppNavGraph()
            }
        }
    }
}