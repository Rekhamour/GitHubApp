package com.example.githubapp.data.db

import androidx.room.*
import com.example.githubapp.data.model.UserData

@Dao
interface UserDao {
    @Insert()
     suspend fun insertUsers(users: List<UserData>?)

    @Query("DELETE FROM user_table WHERE author = :userName")
    suspend fun deleteUser(userName: String?)

    @Query("DELETE FROM user_table")
    suspend  fun deleteAll()

    @Query("SELECT * FROM user_table")
     suspend fun  getAllUsers(): List<UserData>

}