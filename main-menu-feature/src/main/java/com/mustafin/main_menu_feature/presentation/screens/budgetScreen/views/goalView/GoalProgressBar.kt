package com.mustafin.main_menu_feature.presentation.screens.budgetScreen.views.goalView

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.mustafin.main_menu_feature.R

/*
Прогрес бар для отображения прогресса выполнения цели.
Настроенна анимация при изменении значения.
Цвет меняется от красного к зеленому.
*/
@Composable
fun GoalProgressBar(goalProgress: Float) {
    val animatedProgress by animateFloatAsState(
        targetValue = goalProgress,
        label = "progressAnimation"
    )

    val progressColor = lerp(Color.Red, Color.Green, goalProgress)

    LinearProgressIndicator(
        progress = { animatedProgress },
        modifier = Modifier
            .fillMaxWidth()
            .height(8.dp),
        color = progressColor,
        trackColor = colorResource(id = R.color.secondary_background),
        strokeCap = StrokeCap.Round,
        drawStopIndicator = {},
        gapSize = (-8).dp
    )
}