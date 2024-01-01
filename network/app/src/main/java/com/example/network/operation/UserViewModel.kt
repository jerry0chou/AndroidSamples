package com.example.network.operation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
//    private val _user = mutableStateOf<User?>(null)
//    val user: State<User?> = _user

    suspend fun fetchUser(userId: Int): User {
        return UserNetwork.userAPI.getUserById(userId)
    }

    suspend fun getUsers(map: Map<String, String>): List<User>{
        return UserNetwork.userAPI.getUserList(map)
    }
}