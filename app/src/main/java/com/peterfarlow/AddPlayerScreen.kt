package com.peterfarlow

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.peterfarlow.core.TokenColor
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@Composable
fun AddPlayerScreen(startNewGameViewModel: StartNewGameViewModel = viewModel()) {
    var selectedColor by remember { mutableStateOf<TokenColor?>(null) }
    val defaultText = stringResource(R.string.enter_player_name)
    var text by remember { mutableStateOf(defaultText) }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TextField(value = text,
            onValueChange = { text = it })
    }
}

@Composable
fun TokenColorPicker(onColorSelectedListener: (TokenColor) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {

    }
}
