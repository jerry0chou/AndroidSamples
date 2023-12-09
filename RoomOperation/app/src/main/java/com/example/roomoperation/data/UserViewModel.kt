package com.example.roomoperation.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application): AndroidViewModel(application) {
    private val readAllData:  LiveData<List<User>>
    private val repository: UserRepository

    init {
        val useDao = UserDatabase.getDatabase(application).userDao()
        repository = UserRepository(useDao)
        readAllData = repository.readAllData
    }
    fun addUser(user: User){
        viewModelScope.launch(Dispatchers.IO) { repository.addUser(user)  }
    }
}