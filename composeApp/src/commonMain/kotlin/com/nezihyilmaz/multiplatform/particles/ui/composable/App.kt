package com.nezihyilmaz.multiplatform.particles.ui.composable

import androidx.compose.runtime.Composable
import com.nezihyilmaz.multiplatform.particles.controller.Engine
import com.nezihyilmaz.multiplatform.particles.ui.AppTheme

@Composable
internal fun App() = AppTheme {
    Space(Engine())
}