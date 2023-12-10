package com.example.roomoperation.data

import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDao: UserDao) {
    val readAllData: Flow<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }
}