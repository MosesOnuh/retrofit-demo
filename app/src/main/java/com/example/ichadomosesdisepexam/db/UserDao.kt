package com.example.ichadomosesdisepexam.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.ichadomosesdisepexam.model.ProfileResponse


@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: ProfileResponse): Long

    @Query("SELECT * FROM userProfile")
    fun getAllusers(): LiveData<List<ProfileResponse>>

    @Delete
    suspend fun deleteuser(user: ProfileResponse)
}