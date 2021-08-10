package com.peterfarlow

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.peterfarlow.core.TokenColor

@Composable
fun AddPlayerScreen(
    startNewGameViewModel: StartNewGameViewModel = viewModel(),
    navController: NavController
) {
    var selectedColor by remember { mutableStateOf<TokenColor?>(null) }
    var playerName by remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TextField(value = playerName,
            onValueChange = { playerName = it },
            placeholder = { Text(stringResource(R.string.enter_player_name)) })
        Button(onClick = {
            val color = selectedColor ?: return@Button
            if (playerName.isBlank()) return@Button
            startNewGameViewModel.addPlayer(playerName, color)
            navController.popBackStack()
        }
        ) {
            Text(stringResource(R.string.add_player))
        }
        TokenColorPicker(selectedColor, onColorSelectedListener = { selectedColor = it })
    }
}

@Composable
fun TokenColorPicker(
    selectedColor: TokenColor?,
    onColorSelectedListener: (TokenColor) -> Unit
) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        val modifier = Modifier
            .padding(2.dp)
            .weight(1f)
        TokenColorIcon(
            selected = selectedColor == TokenColor.RED,
            color = TokenColor.RED,
            modifier = modifier,
            onColorSelectedListener = onColorSelectedListener
        )
        TokenColorIcon(
            selected = selectedColor == TokenColor.BLUE,
            color = TokenColor.BLUE,
            modifier = modifier,
            onColorSelectedListener = onColorSelectedListener
        )
        TokenColorIcon(
            selected = selectedColor == TokenColor.PURPLE,
            color = TokenColor.PURPLE,
            modifier = modifier,
            onColorSelectedListener = onColorSelectedListener
        )
        TokenColorIcon(
            selected = selectedColor == TokenColor.GREEN,
            color = TokenColor.GREEN,
            modifier = modifier,
            onColorSelectedListener = onColorSelectedListener
        )
        TokenColorIcon(
            selected = selectedColor == TokenColor.YELLOW,
            color = TokenColor.YELLOW,
            modifier = modifier,
            onColorSelectedListener = onColorSelectedListener
        )
    }
}

@Composable
fun TokenColorIcon(
    selected: Boolean = false,
    color: TokenColor,
    modifier: Modifier,
    onColorSelectedListener: (TokenColor) -> Unit
) {
    val border = if (selected) {
        BorderStroke(4.dp, MaterialTheme.colors.onBackground)
    } else {
        null
    }
    Button(
        onClick = { onColorSelectedListener(color) },
        border = border,
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .aspectRatio(1f)
                .clip(CircleShape)
                .background(color.asComposeColor())
        )
    }
}
