package com.example.githubapp.network.service

import com.example.githubapp.data.model.UserData

import retrofit2.http.GET


interface UserApiService {

 @GET("repositories")
    suspend   fun getUsers():List<UserData>
}