package com.example.ichadomosesdisepexam.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity (tableName = "userProfile")
data class ProfileResponse(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @SerializedName("avatar_url")
    val profileImage:String,
    val name: String,
    val followers: Int,
    val following: Int,

) : Serializable