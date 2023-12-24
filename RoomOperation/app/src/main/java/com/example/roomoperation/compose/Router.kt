package com.example.roomoperation.compose

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.roomoperation.data.UserViewModel
import com.example.roomoperation.utils.Param

enum class Operation{
    ADD,
    EDIT
}

sealed class Screen(val route: String) {
    data object ListView : Screen("List")
    data object InputView:  Screen("Input")
}

data class InputParams(val operation: Operation, val userId: Int? = null): Param()
const val InputParamsKey = "InputParamsKey"

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
