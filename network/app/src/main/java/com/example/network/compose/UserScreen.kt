package com.example.network.compose

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.network.operation.UserViewModel
import kotlinx.coroutines.launch

const val TAG = "Network"
@Composable
fun UserScreen(userViewModel: UserViewModel){
    val scope = rememberCoroutineScope()
    var user by remember { mutableStateOf("") }
    var userList by remember { mutableStateOf("") }

    Column {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Button(onClick = {
                scope.launch {
                    val u = userViewModel.fetchUser((Math.random()*100+1).toInt())
                    user = u.toString()
                    Log.d(TAG, "UserScreen: $u")
                }
            },
            ) {
                Text(text = "GetUser")
            }
        }
        Text(text = user)

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Button(onClick = {
                scope.launch {
                    userList = userViewModel.getUsers(mapOf("sort" to "desc", "limit" to "10")).toString()
                }
            },
            ) {
                Text(text = "GetUserList")
            }
        }
        Text(text = userList)
    }
    

}