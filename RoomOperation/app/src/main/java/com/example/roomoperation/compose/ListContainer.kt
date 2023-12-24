package com.example.roomoperation.compose

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.roomoperation.data.User
import com.example.roomoperation.data.UserViewModel
 import com.example.roomoperation.utils.toStr

const val TAG = "ROOM"

@Composable
fun ListItem(user: User, navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .background(Color.Magenta)
            .clickable {
                Log.d(TAG, "$user")
                navController.currentBackStackEntry?.savedStateHandle?.set(InputParamsKey, InputParams(Operation.EDIT, user.id).toStr())
                navController.navigate(Screen.InputView.route)
            }
            .padding(15.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = user.id.toString(),
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Text(text = user.firstName, color = Color.White, fontSize = 20.sp)
        Text(text = user.lastName, color = Color.White, fontSize = 20.sp)
        Text(text = user.age.toString(), color = Color.White, fontSize = 20.sp)
    }
}

@Composable
fun ListScreen(navController: NavController, userVM: UserViewModel) {
    val userList by userVM.allUsers.collectAsState(initial = emptyList())

    Column(modifier = Modifier.fillMaxWidth()) {
        LazyColumn(modifier = Modifier.weight(0.9f), contentPadding = PaddingValues(10.dp)) {
            items(items = userList) {
                Spacer(modifier = Modifier.height(10.dp))
                ListItem(user = it, navController)
            }
        }

        Row(
            modifier = Modifier
                .weight(0.1f)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {
                    Log.d(TAG, "ListScreen: add")
                    navController.currentBackStackEntry?.savedStateHandle?.set(InputParamsKey, InputParams(Operation.ADD).toStr())
                    navController.navigate(Screen.InputView.route)
                }

            ) {
                Text(text = "Add")
            }
        }
    }
}