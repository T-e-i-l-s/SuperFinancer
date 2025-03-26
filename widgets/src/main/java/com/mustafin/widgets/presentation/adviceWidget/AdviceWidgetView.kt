package com.mustafin.widgets.presentation.adviceWidget

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceModifier
import androidx.glance.action.clickable
import androidx.glance.appwidget.action.actionStartActivity
import androidx.glance.background
import androidx.glance.layout.Column
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextAlign
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import com.mustafin.widgets.R
import org.json.JSONArray
import java.io.InputStreamReader

/* Интерфейс виджета с советом */
@SuppressLint("RestrictedApi")
@Composable
fun AdviceWidgetView(context: Context) {
    val advices = loadAdvices(context)
    val randomAdvice = advices.random()

    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("superfinancer://open"))

    Column(
        modifier = GlanceModifier
            .fillMaxWidth()
            .background(ColorProvider(R.color.background))
            .clickable(onClick = actionStartActivity(intent))
            .padding(16.dp)
    ) {
        Text(
            text = randomAdvice,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = TextUnit(18f, TextUnitType.Sp),
                color = ColorProvider(R.color.content)
            )
        )

        Spacer(GlanceModifier.height(8.dp))

        Text(
            text = context.getString(R.string.advices),
            style = TextStyle(
                fontWeight = FontWeight.Normal,
                fontSize = TextUnit(14f, TextUnitType.Sp),
                color = ColorProvider(R.color.content),
                textAlign = TextAlign.End
            ),
            modifier = GlanceModifier.fillMaxWidth()
        )
    }
}

/* Загружаем список советов из assets */
fun loadAdvices(context: Context): List<String> {
    val jsonString = context.assets.open("advices.json").use { inputStream ->
        InputStreamReader(inputStream).readText()
    }
    val jsonArray = JSONArray(jsonString)
    return List(jsonArray.length()) { index ->
        jsonArray.getString(index)
    }
}