package com.example.ichadomosesdisepexam.model

data class UsersResponse(
    val incomplete_results: Boolean,
    val items: List<Item>,
    val total_count: Int
)