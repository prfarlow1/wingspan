package com.peterfarlow

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.contentColorFor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.peterfarlow.core.Player
import com.peterfarlow.core.TokenColor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@Composable
fun NewGamePlayerListScreen(
    startNewGameViewModel: StartNewGameViewModel = viewModel(),
    navController: NavController
) {
    val flow = startNewGameViewModel.playerFlow
    val lifecycleOwner = LocalLifecycleOwner.current
    val playerFlowLifecycleAware = remember(flow, lifecycleOwner) {
        flow.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
    }
    val players by playerFlowLifecycleAware.collectAsState(flow.value)
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.End
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .weight(1f)
        ) {
            for (player in players) {
                PlayerCard(player, onClick = { startNewGameViewModel.deletePlayer(player) })
            }
        }
        FloatingActionButton(
            onClick = { navController.navigate("add_player") },
            modifier = Modifier
        ) {
            Icon(Icons.Default.Add, stringResource(R.string.add_player))
        }

    }
}

@Composable
fun PlayerCard(player: Player, onClick: (Player) -> Unit) {
    val backgroundColor = player.tokenColor.asComposeColor()
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        backgroundColor = backgroundColor
    ) {
        ClickableText(
            text = AnnotatedString(player.name),
            style = TextStyle(color = contentColorFor(backgroundColor)),
            onClick = { onClick(player) })
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

    companion object {
        private val defaultPlayers = listOf(
            Player(0, "Peter", TokenColor.BLUE),
            Player(1, "Alex", TokenColor.YELLOW),
            Player(2, "T.J.", TokenColor.GREEN)
        )
    }

    private val _playerFlow = MutableStateFlow(defaultPlayers)
    val playerFlow: StateFlow<List<Player>> = _playerFlow

    fun addPlayer(playerName: String, color: TokenColor) {
        _playerFlow.update {
            it + Player(nextId(), playerName, color)
        }
    }

    fun deletePlayer(player: Player) {
        _playerFlow.update {
            it - player
        }
    }

    private fun nextId(): Int {
        return _playerFlow.value.maxByOrNull { it.id }?.id ?: 0 + 1
    }
}
