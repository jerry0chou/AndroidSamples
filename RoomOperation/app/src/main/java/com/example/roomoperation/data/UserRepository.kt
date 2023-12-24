package com.example.roomoperation.data

import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDao: UserDao) {
    val readAllData: Flow<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }

    suspend fun getUserById(userId: Int): User? {
        return userDao.getUserById(userId)
    }

    suspend fun updateUserInfo(newUser: User){
        val user = userDao.getUserById(newUser.id)
        user?.let { userDao.updateUser(it) }
    }
}