package com.example.ichadomosesdisepexam.Retrofit

import com.example.ichadomosesdisepexam.model.ProfileResponse
import com.example.ichadomosesdisepexam.model.UsersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface UsersApi {
    @GET("search/users?q=lagos")
    suspend fun getUsers(): Response<UsersResponse>
    @GET ("users/{user}")
    suspend fun getUserProfile(@Path("user") user:String): Response<ProfileResponse>
}