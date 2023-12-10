package com.example.roomoperation.data

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class UserViewModel(context: Context): AndroidViewModel(Application()) {
    private val repository: UserRepository
    val allUsers:  Flow<List<User>>

    init {
        val useDao = UserDatabase.getDatabase(context).userDao()
        repository = UserRepository(useDao)
        allUsers = repository.readAllData
    }
    fun addUser(user: User){
        viewModelScope.launch(Dispatchers.IO) { repository.addUser(user)  }
    }
}