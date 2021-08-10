package com.peterfarlow

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.peterfarlow.core.TokenColor
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@Composable
fun StartNewGameScreen(navController: NavController, startNewGameViewModel: StartNewGameViewModel = viewModel()) {
    Text("start new game")
    PlayerCard(name = "Peter", tokenColor = TokenColor.BLUE)
    PlayerCard(name = "Alex", tokenColor = TokenColor.YELLOW)
    PlayerCard(name = "T.J.", tokenColor = TokenColor.PURPLE)
    FloatingActionButton(onClick = { /*do something*/ }) {
        Icon(Icons.Filled.Favorite, contentDescription = "Localized description")
    }
}

@Composable
fun PlayerCard(name: String, tokenColor: TokenColor) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        backgroundColor = tokenColor.asComposeColor()
    ) {
        Text(name)
    }
}

fun TokenColor.asComposeColor(): Color = when (this) {
    TokenColor.RED -> Color.Red
    TokenColor.BLUE -> Color.Blue
    TokenColor.PURPLE -> Color(0xff8e24aa)
    TokenColor.GREEN -> Color.Green
    TokenColor.YELLOW -> Color.Yellow
}

@HiltViewModel
class StartNewGameViewModel @Inject constructor(
    @Suppress("unused") private val savedStateHandle: SavedStateHandle,
) : ViewModel()
