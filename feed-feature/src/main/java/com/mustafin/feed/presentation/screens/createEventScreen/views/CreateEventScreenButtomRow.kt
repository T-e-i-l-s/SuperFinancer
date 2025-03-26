package com.mustafin.feed.presentation.screens.createEventScreen.views

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mustafin.feed.R
import com.mustafin.ui_components.presentation.buttons.CustomTinyButton

/* View нижнего бара в эдиторе */
@Composable
fun CreateEventScreenBottomRow(
    isCreateEnabled: Boolean,
    createEvent: () -> Unit,
    addImage: (ByteArray) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ImagePickerButton(addImage)

        CustomTinyButton(
            text = stringResource(id = R.string.publish),
            onClick = createEvent,
            enabled = isCreateEnabled
        )
    }
}

/* Функция с кнопкой, которая позволяет прикрепить картинку к событию */
@Composable
private fun ImagePickerButton(addImage: (ByteArray) -> Unit) {
    // Создаем лаунчер для загрузки изображений
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            val byteArray = context.contentResolver.openInputStream(it)?.use { inputStream ->
                inputStream.readBytes()
            }
            byteArray?.let { bytes ->
                addImage(bytes)
            }
        }
    }

    Icon(
        painter = painterResource(id = R.drawable.image),
        contentDescription = null,
        tint = colorResource(id = R.color.content),
        modifier = Modifier
            .clip(CircleShape)
            .size(24.dp)
            .clickable(onClick = { launcher.launch("image/*") })
    )
}