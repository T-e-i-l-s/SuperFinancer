package com.mustafin.feed.presentation.screens.createEventScreen

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mustafin.feed.data.repositories.eventsRepository.EventsRepository
import com.mustafin.feed.utils.events.EventModel
import com.mustafin.feed.utils.events.LinkedNewsModel
import com.mustafin.feed.utils.events.Tags
import com.mustafin.feed.utils.images.compressBitmap
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

/* ViewModel экрана создания поста */
@HiltViewModel
class CreateEventScreenViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val eventsRepository: EventsRepository
) : ViewModel() {
    private val _createEventEnabled = MutableStateFlow(false)
    val createEventEnabled: StateFlow<Boolean> = _createEventEnabled

    private val _selectedTags = MutableStateFlow<Set<Tags>>(emptySet())
    val selectedTags: StateFlow<Set<Tags>> = _selectedTags

    private val _text = MutableStateFlow("")
    val text: StateFlow<String> = _text

    private val _imagesPaths = MutableStateFlow<List<String>>(emptyList())
    val imagesPaths: StateFlow<List<String>> = _imagesPaths

    fun toggleTagSelection(tag: Tags) {
        if (selectedTags.value.contains(tag)) {
            _selectedTags.value = selectedTags.value.minus(tag)
        } else {
            _selectedTags.value = selectedTags.value.plus(tag)
        }
        checkEventEnabled()
    }

    fun setText(text: String) {
        _text.value = text
        checkEventEnabled()
    }

    fun removeImageAtIndex(index: Int) {
        _imagesPaths.value = imagesPaths.value.toMutableList().apply { removeAt(index) }
    }

    private fun checkEventEnabled() {
        _createEventEnabled.value = text.value.isNotBlank() && _selectedTags.value.isNotEmpty()
    }


    fun addImage(imageByteArray: ByteArray) {
        viewModelScope.launch {
            val bitmap = BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.size)
            val compressedBitmap = bitmap.compressBitmap(1000, 1000)
            val imageUri =
                saveImageToInternalStorage(compressedBitmap, "${System.currentTimeMillis()}")
            _imagesPaths.value = imagesPaths.value.toMutableList().apply { add(imageUri) }
        }
    }

    // Функция, которая сохраняет изображения в локальной области приложения
    private suspend fun saveImageToInternalStorage(bitmap: Bitmap, imageName: String): String {
        return withContext(Dispatchers.IO) {
            val directory = context.filesDir
            val file = File(directory, imageName)

            val fileOutputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream)
            fileOutputStream.flush()
            fileOutputStream.close()

            file.absolutePath
        }
    }


    fun createEvent(linkedNews: LinkedNewsModel?, whenCompleted: () -> Unit) {
        viewModelScope.launch {
            eventsRepository.addEvent(
                EventModel(
                    images = imagesPaths.value,
                    text = text.value,
                    tags = selectedTags.value,
                    linkedNews = linkedNews
                )
            )
            whenCompleted()
        }
    }
}