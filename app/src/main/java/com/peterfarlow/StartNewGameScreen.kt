package com.peterfarlow

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@Composable
fun StartNewGameScreen(navController: NavController, startNewGameViewModel: StartNewGameViewModel = viewModel()) {
    navController.graph
    startNewGameViewModel.hashCode()
    Text("start new game")
}

@HiltViewModel
class StartNewGameViewModel @Inject constructor(
    @Suppress("unused") private val savedStateHandle: SavedStateHandle,
) : ViewModel()
