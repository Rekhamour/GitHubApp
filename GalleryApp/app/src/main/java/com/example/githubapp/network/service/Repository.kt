package com.example.githubapp.network.service

import android.util.Log
import com.example.githubapp.data.db.UserDao
import com.example.githubapp.data.model.UserData
import javax.inject.Inject


class Repository  @Inject constructor(var userDao: UserDao, var userApiService: UserApiService) {
   var TAG:String="Repository"
    suspend fun getUserfromApi(): List<UserData> {
        try{
            return userApiService.getUsers()
        } catch(e:IllegalStateException) {
            Log.e(TAG,"exception" + e.message)
        }
        return emptyList<UserData>()
    }

    suspend fun getUsersFromDb(): List<UserData> {
        try {
            return userDao.getAllUsers()
        } catch(e:IllegalStateException) {
            Log.e(TAG,"exception" + e.message)
        }

        return emptyList<UserData>()
    }

    suspend fun insertUsersToDB(users:List<UserData>){
        try {
            userDao.insertUsers(users)
        }catch(e:IllegalStateException) {
            Log.e(TAG,"exception" + e.message)
        }
    }

    suspend fun deleteUsersfromDB(){
        try{
            userDao.deleteAll()
        }catch(e:IllegalStateException) {
            Log.e(TAG,"exception" + e.message)
        }
    }

}

