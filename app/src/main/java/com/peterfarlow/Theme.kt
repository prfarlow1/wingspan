package com.peterfarlow

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val blue500 = Color(0xff6002ee)
val green400 = Color(0xff90ee02)
val blue100 = Color(0xffd4bff9)
val indigo = Color(0xff303F9F)
val grayBlue = Color(0xFFC5CAE9)

private val DarkColors = darkColors()
private val LightColors = lightColors()

@Composable
fun WingspanTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColors
    } else {
        LightColors
    }

    MaterialTheme(colors = colors) {
        content()
    }
}
