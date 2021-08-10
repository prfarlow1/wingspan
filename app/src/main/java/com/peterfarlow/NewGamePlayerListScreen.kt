package com.peterfarlow

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.contentColorFor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.peterfarlow.core.Player
import com.peterfarlow.core.TokenColor
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@Composable
fun NewGamePlayerListScreen(
    startNewGameViewModel: StartNewGameViewModel = viewModel(),
    navController: NavController
) {
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.End
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .weight(1f)
        ) {
            val players = startNewGameViewModel.getPlayers()
            for (player in players) {
                PlayerCard(name = player.name, tokenColor = player.tokenColor)
            }
        }
        FloatingActionButton(onClick = { navController.navigate("add_player") },
            modifier = Modifier
        ) {
            Icon(Icons.Default.Add, stringResource(R.string.add_player))
        }

    }

}

@Composable
fun PlayerCard(name: String, tokenColor: TokenColor) {
    val backgroundColor = tokenColor.asComposeColor()
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        backgroundColor = backgroundColor
    ) {
        Text(name, color = contentColorFor(backgroundColor))
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
) : ViewModel() {

    private val players: MutableList<Player> = mutableListOf()

    fun getPlayers(): List<Player> {
        if (players.isNotEmpty()) return players
        val peter = Player(0, "Peter", TokenColor.BLUE)
        val alex = Player(1, "Alex", TokenColor.YELLOW)
        val tj = Player(2, "T.J.", TokenColor.GREEN)
        return listOf(peter, alex, tj)
    }

    fun addPlayer(playerName: String, color: TokenColor) {
        players += Player(nextId(), playerName, color)
    }

    fun nextId(): Int {
        return players.maxByOrNull { it.id }?.id ?: 0 + 1
    }
}
