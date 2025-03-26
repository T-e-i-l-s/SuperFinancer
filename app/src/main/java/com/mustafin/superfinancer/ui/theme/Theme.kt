package com.mustafin.superfinancer.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun SuperFinancerTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        typography = Typography,
        content = content
    )
}