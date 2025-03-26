package com.mustafin.search_feature.presentation.screens.searchScreen.views

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mustafin.search_feature.R

/* Кнопка, которая перенаправляет пользователя в гугл с его поисковым запросом */
@Composable
fun InternetSearchButton(query: String) {
    val context = LocalContext.current

    Row(
        Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .clickable(onClick = {
                val intent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://www.google.com/search?q=$query")
                )
                context.startActivity(intent)
            })
            .background(colorResource(id = R.color.secondary_background).copy(0.5f))
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = R.string.internet_search),
            style = MaterialTheme.typography.titleMedium,
            color = colorResource(id = R.color.content),
            modifier = Modifier.weight(1f)
        )

        Icon(
            painter = painterResource(id = R.drawable.chevron_right),
            contentDescription = null,
            tint = colorResource(id = R.color.content)
        )
    }
}