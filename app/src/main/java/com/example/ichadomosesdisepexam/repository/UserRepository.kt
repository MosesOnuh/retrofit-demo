package com.example.ichadomosesdisepexam.repository

import com.example.ichadomosesdisepexam.Retrofit.RetrofitInstance
import com.example.ichadomosesdisepexam.db.UserDatabase
import com.example.ichadomosesdisepexam.model.ProfileResponse


class UserRepository constructor(val database: UserDatabase){
    suspend fun getUsers()= RetrofitInstance.api.getUsers()
    suspend fun getUserProfile(user: String) = RetrofitInstance.api.getUserProfile(user)

    //Database Query functions
    suspend fun insertUser(user: ProfileResponse) = database.getUserDao().insertUser(user)
    fun getSavedUser() = database.getUserDao().getAllusers()
    suspend fun deteUser(user: ProfileResponse) = database.getUserDao().deleteuser(user)

}
