package com.mustafin.main_menu_feature.presentation.screens.homeScreen.views.newsList

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.mustafin.core.utils.news.NewsModel
import com.mustafin.main_menu_feature.R
import com.mustafin.ui_components.presentation.buttons.CustomTinyButton
import java.time.LocalDateTime

/* Интерфейс блока с краткной информацией о новости */
@Composable
fun NewsView(news: NewsModel, onClick: () -> Unit) {
    val context = LocalContext.current

    HorizontalDivider(
        modifier = Modifier.fillMaxWidth(),
        thickness = 1.dp,
        color = colorResource(id = R.color.secondary_background)
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
            .clickable(onClick = onClick),
        horizontalAlignment = Alignment.Start
    ) {
        NewsViewHeader(news)

        Spacer(modifier = Modifier.height(16.dp))

        news.imageUrl?.let { imageUrlSafe ->
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(imageUrlSafe)
                    .diskCacheKey(imageUrlSafe)
                    .build(),
                contentDescription = null,
                modifier = Modifier
                    .background(colorResource(id = R.color.secondary_background))
                    .fillMaxWidth()
                    .height(250.dp),
                contentScale = ContentScale.FillWidth
            )

            Spacer(modifier = Modifier.height(16.dp))
        }

        Text(
            text = news.title,
            style = MaterialTheme.typography.titleMedium,
            color = colorResource(id = R.color.content),
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        news.description.takeIf { it.isNotBlank() }?.let {
            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = it,
                style = MaterialTheme.typography.labelMedium,
                color = colorResource(id = R.color.content),
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        CustomTinyButton(
            text = stringResource(id = R.string.share),
            onClick = {
                val intent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT, news.link)
                }
                context.startActivity(Intent.createChooser(intent, news.title))
            },
            icon = painterResource(id = R.drawable.share),
            contentColor = colorResource(id = R.color.blue),
            backgroundColor = colorResource(id = R.color.blue).copy(0.2f),
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
}

@Composable
@Preview
private fun Preview() {
    NewsView(
        news = NewsModel(
            LoremIpsum(2).values.first(),
            LoremIpsum(20).values.first(),
            "",
            "",
            LoremIpsum(1).values.first(),
            LocalDateTime.now()
        )
    ) {}
}