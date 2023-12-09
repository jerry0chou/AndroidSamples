package com.example.roomoperation.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


sealed class Screen(val route: String) {
    object ListView : Screen("List")
    object InputView : Screen("Input")
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun Router(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.ListView.route) {
        composable(Screen.ListView.route) {
            ListScreen(navController)
        }
        composable(Screen.InputView.route) {
            InputScreen(navController)
        }
    }
}