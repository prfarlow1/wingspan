package com.peterfarlow

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeScreen()
        }
    }
}

@Composable
fun HomeScreen() {
    WingspanTheme {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "home") {
            composable(route = "home") {
                MainEntryPointScreen(navController)
            }
            navigation(startDestination = "player_list", route = "start_new_game") {
                composable(route = "player_list") {
                    val parentViewModel = hiltViewModel<StartNewGameViewModel>(
                        navController.getBackStackEntry("start_new_game")
                    )
                    NewGamePlayerListScreen(parentViewModel, navController)
                }
                composable(route = "add_player") {
                    val parentViewModel = hiltViewModel<StartNewGameViewModel>(
                        navController.getBackStackEntry("start_new_game")
                    )
                    AddPlayerScreen(parentViewModel, navController)
                }
            }
            composable(route = "settings") {
                SettingsScreen()
            }
            composable(route = "about") {
                AboutScreen(navController)
            }
        }
    }
}

@Composable
fun MainEntryPointScreen(navController: NavController) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Spacer(modifier = Modifier.size(16.dp))
        Button(onClick = { navController.navigate("start_new_game") }) {
            Text(
                text = "Start new game",
            )
        }
        Spacer(modifier = Modifier.size(16.dp))
        Button(onClick = { navController.navigate("settings") }) {
            Text("Settings")
        }
        Spacer(modifier = Modifier.size(16.dp))
        Button(onClick = { navController.navigate("about") }) {
            Text("About")
        }
        Spacer(modifier = Modifier.size(16.dp))
    }
}

@Composable
fun SettingsScreen() {
    Text("settings")
}

@Composable
fun AboutScreen(navController: NavController) {
    navController.graph
    Text("about app")
}

@Composable
@Preview
fun DefaultPreview() {
    HomeScreen()
}
