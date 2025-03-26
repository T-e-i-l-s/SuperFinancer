package com.mustafin.feed.utils.images

import android.graphics.Bitmap

/* Экстеншн, который сжимает bitmap для хранения в sqllite */
fun Bitmap.compressBitmap(maxWidth: Int, maxHeight: Int): Bitmap {
    val aspectRatio = width.toFloat() /height.toFloat()
    val newWidth: Int
    val newHeight: Int

    if (width > height) {
        newWidth = maxWidth
        newHeight = (newWidth / aspectRatio).toInt()
    } else {
        newHeight = maxHeight
        newWidth = (newHeight * aspectRatio).toInt()
    }

    return Bitmap.createScaledBitmap(this, newWidth, newHeight, false)
}