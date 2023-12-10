package com.example.roomoperation.compose

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.roomoperation.data.UserViewModel


sealed class Screen(val route: String) {
    object ListView : Screen("List")
    object InputView : Screen("Input")
}

@Composable
fun Router(context: Context){
    val navController = rememberNavController()
    val userVM = UserViewModel(context)
    NavHost(navController = navController, startDestination = Screen.ListView.route) {
        composable(Screen.ListView.route) {
            ListScreen(navController,userVM )
        }
        composable(Screen.InputView.route) {
            InputScreen(navController, userVM)
        }
    }
}