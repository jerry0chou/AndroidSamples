package com.example.roomoperation.data

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
const val TAG = "ROOM"
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
    // 添加更新用户信息的函数
    fun updateUserInfo(newUser: User) {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d(TAG, "UserViewModel updateUserInfo: $newUser")
            repository.updateUserInfo(newUser)
        }
    }

    suspend fun findUserById(userId: Int): User? {
        return repository.getUserById(userId)
    }

    fun deleteUser(user: User){
        viewModelScope.launch(Dispatchers.IO) { repository.deleteUser(user) }
    }
}