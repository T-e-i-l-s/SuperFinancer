package com.mustafin.superfinancer.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.mustafin.superfinancer.R

val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.geologica)),
        fontSize = 26.sp,
        fontWeight = FontWeight.Black
    ),

    titleLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.geologica)),
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold
    ),

    titleMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.geologica)),
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold
    ),

    titleSmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.geologica)),
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold
    ),

    labelLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.geologica)),
        fontSize = 20.sp,
        fontWeight = FontWeight.Medium
    ),

    labelMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.geologica)),
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium
    ),

    labelSmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.geologica)),
        fontSize = 12.sp,
        fontWeight = FontWeight.Medium
    )
)