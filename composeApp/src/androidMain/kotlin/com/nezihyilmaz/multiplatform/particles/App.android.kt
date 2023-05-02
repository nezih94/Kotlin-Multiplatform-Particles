package com.nezihyilmaz.multiplatform.particles

import android.app.Application
import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.nezihyilmaz.multiplatform.particles.ui.composable.App

class AndroidApp : Application() {
    companion object {
        lateinit var INSTANCE: AndroidApp
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }
}

class AppActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        window.navigationBarColor = Color.BLACK
        super.onCreate(savedInstanceState)
        setContent { App() }
    }
}

actual val DEFAULT_RESOURCE = FormattedSVGResource.ANDROID_ICON_AND_TEXT