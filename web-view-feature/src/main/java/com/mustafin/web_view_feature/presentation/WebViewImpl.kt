package com.mustafin.web_view_feature.presentation

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

/* Реализация функционала WebView */
@Composable
fun WebViewImpl(url: String, modifier: Modifier = Modifier) {
    AndroidView(
        factory = { context ->
            WebView(context).apply {
                webViewClient = WebViewClient()
                settings.javaScriptEnabled = true
                settings.mediaPlaybackRequiresUserGesture = false
                loadUrl(url)
            }
        },
        update = { webView ->
            webView.loadUrl(url)
        },
        modifier = modifier
    )
}
