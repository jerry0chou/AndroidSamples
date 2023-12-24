package com.example.roomoperation.compose

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.roomoperation.data.User
import com.example.roomoperation.data.UserViewModel
import com.example.roomoperation.utils.shortToast

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputScreen(navController: NavController, userVM: UserViewModel) {
    var firstName by rememberSaveable { mutableStateOf("") }
    var lastName by rememberSaveable { mutableStateOf("") }
    var age by rememberSaveable { mutableStateOf("") }
    val context = LocalContext.current

    LaunchedEffect(key1 = Unit){
        Log.d(TAG, "InputScreen: init")
        val data =  navController.previousBackStackEntry?.savedStateHandle?.get<String>(InputParamsKey)
        Log.d(TAG, "InputScreen: $data")
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            OutlinedTextField(
                value = firstName, onValueChange = { firstName = it },
                label = { Text(text = "firstName") },
            )
            OutlinedTextField(
                value = lastName, onValueChange = { lastName = it },
                label = { Text(text = "lastName") },
            )
            OutlinedTextField(
                value = age, onValueChange = { age = it },
                label = { Text(text = "age") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                )
            Spacer(modifier = Modifier.height(10.dp))

            Row{
                Button(onClick = {
                    navController.navigate(Screen.ListView.route)
                }) {
                    Text(text = "Back")
                }

                Spacer(modifier = Modifier.width(10.dp))

                Button(onClick = { Log.d(TAG, "InputScreen: submit")
                    if(firstName.isNotEmpty() && lastName.isNotEmpty() && age.isNotEmpty()){
                        userVM.addUser(User(0, firstName, lastName, age.toInt()))
                        navController.navigate(Screen.ListView.route)
                    }else{
                        context.shortToast("please check you data that is not empty")
                    }
                }) {
                    Text(text = "Submit")
                }
            }

        }

    }
}