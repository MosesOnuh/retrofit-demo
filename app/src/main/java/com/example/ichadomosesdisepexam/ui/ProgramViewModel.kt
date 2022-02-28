package com.example.ichadomosesdisepexam.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ichadomosesdisepexam.model.ProfileResponse
import com.example.ichadomosesdisepexam.model.UsersResponse
import com.example.ichadomosesdisepexam.repository.UserRepository
import com.example.ichadomosesdisepexam.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response


class ProgramViewModel(
     val userRepository: UserRepository
) : ViewModel() {

   val UsersList: MutableLiveData<Resource<UsersResponse>> = MutableLiveData()

    init{
        getAllUsers()
    }

    fun getAllUsers() = viewModelScope.launch {
        UsersList.postValue(Resource.Loading())
        val response = userRepository.getUsers()
        UsersList.postValue(ListOfUsersResponse(response))
    }

    private fun ListOfUsersResponse(response: Response<UsersResponse>) : Resource<UsersResponse> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

val userProfile: MutableLiveData<Resource<ProfileResponse>> = MutableLiveData()

fun fetchUser(link: String) = viewModelScope.launch {
    userProfile.postValue(Resource.Loading())
    val response = userRepository.getUserProfile(link)
    userProfile.postValue(UserProfileResponse(response))
}

private fun UserProfileResponse(response: Response<ProfileResponse>) : Resource<ProfileResponse> {
    if(response.isSuccessful) {
        response.body()?.let { resultResponse ->
            return Resource.Success(resultResponse)
        }
    }
    return Resource.Error(response.message())

}

    fun savedUserProfile(user: ProfileResponse) = viewModelScope.launch{
        userRepository.insertUser(user)
    }

    fun getSavedUser() = userRepository.getSavedUser()

    fun deleteUser (user: ProfileResponse)= viewModelScope.launch {
        userRepository.deteUser(user)
    }

}